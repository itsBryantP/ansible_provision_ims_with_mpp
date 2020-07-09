# Bank of Z

The playbooks in this repo work together to provision IMS 15, 2 MPP regions, 1 JMP region and perform all configuration and setup for the Bank of Z Demo.

This project currently runs on native z/OS systems.
On z/VM, IMS DB/TM, MPP, JMP and zCEE are provisioning correctly, bank configuration is hanging causing z/VM crash.
Please take this into consideration when running [provision-ims-bank.yml](provision-ims-bank.yml)
ongoing.
For this reason, there are no included options for z/VM.

- [Bank of Z](#bank-of-z)
  - [Document Syntax](#document-syntax)
  - [Requirements](#requirements)
  - [Initial setup](#initial-setup)
  - [Run the playbook](#run-the-playbook)
  - [Project structure](#project-structure)
  - [Roles](#roles)
  - [Playbooks: Setup](#playbooks-setup)
    - [authenticate-with-firewall.yml](#authenticate-with-firewallyml)
    - [install-python.yml](#install-pythonyml)
    - [install-zoau.yml](#install-zoauyml)
    - [provision-ims-dbdc.yml](#provision-ims-dbdcyml)
    - [create-mpp-1.yml &amp; create-mpp-2.yml](#create-mpp-1yml-amp-create-mpp-2yml)
    - [create-jmp.yml](#create-jmpyml)
    - [provision-zcee.yml](#provision-zceeyml)
    - [configure-bank.yml](#configure-bankyml)
    - [provision-ims-bank.yml](#provision-ims-bankyml)
  - [Playbooks: Teardown](#playbooks-teardown)
    - [deprovision-ims-dbdc.yml](#deprovision-ims-dbdcyml)
    - [deprovision-zcee.yml](#deprovision-zceeyml)
  - [Playbook Breakdown: State Management](#playbook-breakdown-state-management)
    - [create-stateful-workflow.yml](#create-stateful-workflowyml)
    - [flag-template-for-removal.yml](#flag-template-for-removalyml)
    - [clean-deprovisioned-instances.yml](#clean-deprovisioned-instancesyml)
  - [Miscellaneous Playbooks](#miscellaneous-playbooks)
  - [General help](#general-help)
    - [The installation path for Ansible differs depending on installation method and OS](#the-installation-path-for-ansible-differs-depending-on-installation-method-and-os)
    - [Running playbooks](#running-playbooks)
    - [Using SSH keys](#using-ssh-keys)
  - [z/OS specific Ansible help](#zos-specific-ansible-help)
    - [SSH pipelining](#ssh-pipelining)
    - [EBCDIC vs ASCII Encoding](#ebcdic-vs-ascii-encoding)
    - [Install Python 3.6](#install-python-36)
    - [Install ZOAU (Z Open Automation Utilities)](#install-zoau-z-open-automation-utilities)
  - [Additional Info](#additional-info)
    - [Encrypted Variables: Ansible Vault](#encrypted-variables-ansible-vault)

## Document Syntax

This documentation will use **host** to refer to the system used to start Ansible, **target** will refer to the system to be configured.

## Requirements

* Python 2.7.13 or higher must be installed on the target z/OS system. For information consult the "General Help" section. 
* Python 2.7-3.7 must be installed on the host (system starting Ansible). Python 3 or higher is recommended.
  * To install Python 3.6 on a z/OS system, please consult the [Install Python 3.6](#install-python-36) section.
* Ansible packages must be installed on the host (using *pip*, *aptitude*, *yum* or other package install methods, refer to the [install documentation](https://docs.ansible.com/ansible/latest/installation_guide/intro_installation.html) for more information).
* SSH must be enabled on the target z/OS system.
* Z Open Automation Utilities must be installed. For more information consult the [install ZOAU](#install-zoau-z-open-automation-utilities) section.


## Initial setup

A few settings may need to be changed to ensure compatability with your z/OS target.

For more information on python configuration requirements on z/OS, refer to [Ansible FAQ: Running on z/OS](https://docs.ansible.com/ansible/latest/reference_appendices/faq.html).

1. Change the environment variables, provided as key value pairs in the `system_environment` variable, in the file `host_vars/<system_type>.yml` to match your target's python installation.
    * If you are using Rocket Software's Python Installation, you will likely need all of the provided variables. Change the paths provided to match those of your installation. For example, my python installation is at `/usr/lpp/rsusr/python27/python-2017-04-12-py27/python27/bin` and my environment variables are as follows:

    ```yaml
    environment:
        # environment variables for python
        MANPATH: /usr/man/%L
        FFI_LIB: /usr/lpp/rsusr/python27/python-2017-04-12-py27/python27/lib/ffi
        RELEASE_NAME: python-2017-04-12
        CURL_CA_BUNDLE: /usr/lpp/rsusr/python27/python-2017-04-12-py27/python27/etc/ssl/cacert.pem
        RELEASE_DIR: /usr/lpp/rsusr/python27/python-2017-04-12-py27
        RELEASE_TYPE: py27
        LIBPATH: /usr/lpp/rsusr/python27/python-2017-04-12-py27/python27/lib:/lib:/usr/lib:.
        NLSPATH: /usr/lib/nls/msg/%L/%N
        PATH: /usr/lpp/rsusr/python27/python-2017-04-12-py27/python27/bin:/bin:.
        MAIL: /usr/mail/OMVSADM
        _: /bin/env
        TERMINFO: /usr/lpp/rsusr/python27/python-2017-04-12-py27/python27/share/terminfo
        PYTHON_ENV: python27
        PKG_CONFIG_PATH: /usr/lpp/rsusr/python27/python-2017-04-12-py27/python27/lib/pkgconfig:/usr/lpp/rsusr/python27/python-2017-04-12-py27/python27/share/pkgconfig
        PYTHON_HOME: /usr/lpp/rsusr/python27/python-2017-04-12-py27/python27
    ```  

2. Update the configuration
   * `/host_vars/<system_type>.yml` holds variables to be used by our playbooks. Change values as necessary to meet your configuration. If you have a working z/OSMF workflow, most variables should translate directly.
   * The `defaults/main.yml` file in various roles holds additional default variables related to specific steps of Bank of Z setup.
     The default may need to be changed by updating the contents of `defaults/main.yml` or setting the variables in a location with higher precedence (basically anywhere).
3. Update the hosts file
   * `inventory` contains the information needed to connect to our target. We must specify the following information about our target system:
     * ansible_host: either an IP or URL to the target system.
     * ansible_user: the username used to login with SSH.
     * ansible_python_interpreter: the path on the target to the python interpreter.
   * An example is below, where `z-system` will be the name used to reference our target:
  
    ```yaml
    zsystem:
    hosts:
        zvm:
        ansible_host: ec00000b.vmec.svl.ibm.com
        ansible_user: omvsadm
        ansible_python_interpreter: /usr/lpp/rsusr/python27/python-2017-04-12-py27/python27/bin/python
    ```

    * Multiple systems can be specified in the hosts file, Ansible can run across a cluster.

<!-- 5. [Bzip2 from Rocket Software](https://www.rocketsoftware.com/zos-open-source) is used to decompress files sent to the z/OS node. A copy of bzip2 should be placed in `roles/install-bzip2/files/` or already be installed in the path set for the variable `uss_utilities_path`. -->

## Run the playbook

To run the Ansible playbook and begin provisioning, type the following from the root of this repository:

`ansible-playbook -i inventories/zvm provision-ims-bank.yml`

This command assumes you have a public SSH key on the system we will be using as our Ansible host (specified in the host file). To add your publickey, use the `ssh-copy-id` command. 

`ssh-copy-id -i ~/.ssh/mykey.pub omvsadm@<hostsystemname>.com`

Alternatively, the command `ansible-playbook -i inventories/zvm provision-ims-bank.yml --ask-pass` can be used without needing a publickey on the host system. On some systems (mac) this may significantly impact performance.

This `ansible-playbook` call tells Ansible to run the `provision-ims-bank.yml` playbook, specifying the hosts file with `-i`. The `--ask-pass` flag tells Ansible to ask for the SSH password during runtime instead of failing the playbook. This flag is useful for testing, but is being deprecated. The `ansible_password` can be set in the `hosts` file, or stored in an encrypted file and used with variable substitution.

For debugging output, use `-v`, `-vv`, `-vvv`, or `-vvvv` for increasing levels of verbosity.

## Project structure

* `./inventories` contains the inventory files for z/VM and Native z/OS host information.
* Remaining variables are defined in `./host_vars/`. These files contain variables used in at least 2 of the playbooks.
  These variable files map to an example configuration for either a z/VM or a native z/OS system.
  When an inventory is used, the variable file matching the name of the host will be loaded.
* `./roles` contains any roles used in in or more playbooks.
* `./filter_plugins` contains custom Jinja2 filters used in playbooks.
* `./library` contains custom Ansible modules used in playbooks.

## Roles

This project uses roles to isolate the tasks and resources (files, templates, variables) needed to perform parts of the setup process.

Inside of each role:

* `./files` contains any files used in one or more playbooks.
  Sub-folder names describe the use of the contained files.
* `./templates` contains all templates used in one or more playbooks.
  Each playbook has their own sub-folder; each of these may have additional sub-folders describing their use or order of use.
* `./defaults` contains the default variable values for the role.
  These values have a very low level of precedence.

## Playbooks: Setup

The following section describes playbooks that can be used as part of the Bank of Z setup process.

Each of these playbooks can run independently, provided that any prerequisite steps have been performed before running.

### [authenticate-with-firewall.yml](authenticate-with-firewall.yml)

If a firewall is used to control access to the z/OS system, this playbook can be used to perform necessary authentication.
If the playbooks used in this repo are ran from a local machine that is already authenticated, this playbook is not necessary but should not cause issues.

### [install-python.yml](install-python.yml)

This playbook downloads Python from artifactory and installs it on the z/OS system.  

### [install-zoau.yml](install-zoau.yml)

This playbook downloads ZOAU (Z Open Automation Utilities) and installs it on the z/OS system.  

### [provision-ims-dbdc.yml](provision-ims-dbdc.yml)

This playbook is responsible for provisioning IMS 15 TM/DB and setting up some initial resources for the message regions. Ways to run this playbook [can be found here](https://github.ibm.com/IBMZSoftware/ansible-ims-bank-app/wiki/Provision-IMS-DBDC.yml)

### __create-mpp-1.yml & create-mpp-2.yml__

These playbooks create the two MPPs tailored to the requirements of Bank of Z.
Two separate playbooks are used due to differences in configuration.
While we could have used a single playbook and specified the files to reference to account for the different configurations, using two playbooks is easier to work with when performing playbook chaining in an Ansible Tower workflow template.

### [create-jmp.yml](create-jmp.yml)

This playbook creates the JMP tailored to the requirements of Bank of Z.

### [provision-zcee.yml](provision-zcee.yml)

This playbook provisions and configures a basic zCEE instance.
Additional configuration is handled in _configure-bank.yml_.

### [configure-bank.yml](configure-bank.yml)

This playbook is responsible for performing complete setup and configuration of the Bank of Z application.

### [provision-ims-bank.yml](provision-ims-bank.yml)

This playbook is used to drive provisioning from CLI during testing.
Plays from multiple provisioning playbooks are included for variable propagation between plays.
Variable propagation using this method is comparable to Ansible Tower workflow template behavior.

Roles that drive the following playbooks are included:

* provision-ims-dbdc.yml
* create-mpp-1.yml
* create-mpp-2.yml
* create-jmp.yml
* provision-zcee.yml
* configure-bank.yml

## Playbooks: Teardown

The following playbooks are involved in the teardown process for Bank of Z.

### [deprovision-ims-dbdc.yml](deprovision-ims-dbdc.yml)

This playbook handles teardown steps for IMS 15 and Bank of Z.
The remainder of the Bank of Z teardown is handled in _deprovision-zcee.yml_.

### [deprovision-zcee.yml](deprovision-zcee.yml)

This playbook stops the running zCEE instance and removes related files from the filesystem.

## Playbook Breakdown: State Management

The following playbooks manage the state of provisioned middleware using the Ansible Tower API.

### [create-stateful-workflow.yml](create-stateful-workflow.yml)

This playbook make a copy of an existing Ansible Tower workflow template, assigns any variables needed to reference the provisioned instance, renames it to identify the provisioned instance and changes the permissions so the user that provisioned the instance has execute access.

The workflow to copy should usually be the deprovision workflow for the middleware which was provisioned.
This allows the user to easily find the deprovision playbook that is pre-configured to their instance.

In addition, this playbook is responsible for adding the name of the copied workflow (AKA name related to provisioned instance) to an Ansible Tower inventory which holds the list of provisioned instances.
This list is used to manage what instances currently exist or have been deprovisioned and flagged for deletion.

### [flag-template-for-removal.yml](flag-template-for-removal.yml)

When an instance is deprovisioned, we need to mark the copied deprovision workflow for deletion in Ansible Tower.
This playbook is ran as the last node in a workflow template, or immediately after for testing purposes.

Since we may still be running the workflow which is to be deleted, we must flag the workflow for deletion and rely on a secondary, most likely scheduled, cleanup template to remove any references from Ansible Tower.
We flag the workflow for removal in the inventory file used in [create-stateful-workflow.yml](create-stateful-workflow.yml) and [clean-deprovisioned-instances.yml](clean-deprovisioned-instances.yml).

### [clean-deprovisioned-instances.yml](clean-deprovisioned-instances.yml)

This playbook checks the state management inventory in Ansible Tower to see if any workflows have been flagged for deletion.
If flagged workflows are found, the playbook attempts to remove each workflow.
Upon successful removal of a workflow, it is removed from the inventory.

## Miscellaneous Playbooks

These playbooks have miscellaneous uses that may or may not have been replaced by other methods.

## General help

### The installation path for Ansible differs depending on installation method and OS

__Mac__:

Recommended install method is: Pip

* To install: `pip install ansible`
* For python 3:  `pip3 install ansible`
* Installation path can be found with `pip/pip3 show ansible`

__Linux__:

Install using package manager for distribution such as `pacman`, `yum` or `aptitude`.

Package manager's generally place Ansible in a standard install path.

### Running playbooks

On mac, using SSH keys is required to get full performance and simultaneous connection support.
Using –ask-pass will cause paramiko (inferior python based SSH) to be used.

### Using SSH keys

To generate an SSH key: `ssh-keygen -t rsa -b 4096`

Copy your public key to the target node: `ssh-copy-id -I ~/.ssh/id_rsa.pub target_username@target_address`

## z/OS specific Ansible help

Rocket Python versions earlier than 2.7.13/3.6.1 will not work with Ansible.
Recent versions represent strings in ASCII.

Python on z/OS requires some environment variables to be set in order to be used.
These need to be provided when running an Ansible playbook with z/OS as the target node.

Environment variables can be set at the task or play level, it is recommended to set these environment variables at the play level so they will be applied to all tasks in the play.

### SSH pipelining

According to the docs, ANSIBLE_SSH_PIPELINING should be set to true when z/OS is the target node.
In addition, enabling pipelining significantly increases Ansible performance.

### EBCDIC vs ASCII Encoding

z/OS systems generally use EBCDIC encoding, this can lead to issues when copying files between Unix and z/OS.

There are two transport methods that can be used to send files with Ansible: SCP and SFTP. Each of these behave differently on z/OS.

* SCP
  * Treats files as text
  * Performs ASCII/EBCDIC conversion on files automatically
* SFTP
  * Files transferred between EBCDIC and ASCII platforms are not converted

Ansible has some variables that can be set to force a particular communication method to be used.
  
* _DEFAULT_SCP_ID_SSH_ – set to True to force SCP, if false will use SFTP
* _DEFAULT_SSH_TRANSFER_METHOD_ – unused according to docs

These can be set as environment variables or in `ansible.cfg`

To convert from ASCII to EBCDIC, the following command can be used in z/OS USS

`iconv -f ISO8859-1 -t IBM-1047 ASCII_file_name`

### Install Python 3.6

To install Python 3.6 on your z/OS system, you will first need to obtain an API Key from artifactory. Once you have this API Key, you will need to navigate to `roles/deploy-python36-from-artifactory/vars/main.yml` and add the line:  
`api_key: {ARTIFACTORY API KEY}`  

Within `roles/deploy-python36-from-artifactory/defaults/main.yml`, there are two variables called `copy_dir` and `install_dir`. Ensure that the volume specified in `copy_dir` has at least 500MB of available storage and the volume specified in `install_dir` has at least 2.5 GB.  

You are now setup and ready to run the [install-python.yml](install-python.yml) playbook that will pull Python 3.6 from artifactory and install it onto your z/OS system. You can do so by entering the following command in your terminal:  
`ansible-playbook run -i inventories/zvm install-python.yml`  

__NOTE:__ Expect this playbook to take approximately 30 minutes to complete.

### Install ZOAU (Z Open Automation Utilities)
Steps to install ZOAU manually [can be found here](https://www.ibm.com/support/knowledgecenter/en/SSKFYE_1.0.0/install.html)

__NOTE:__ Python 3.6 is required

The pax for ZOAU [can be obtained here](https://na.artifactory.swg-devops.com/artifactory/webapp/#/artifacts/browse/tree/General/sys-dbb-ga-generic-local/ZOAU-1.0.0/GA)

Of course, the easiest way to install ZOAU is to run the [install-zoau.yml](install-zoau.yml) playbook. To install ZOAU on your z/OS system, you will first need to obtain an API Key from artifactory. Once you have this API Key, you will need to navigate to `roles/deploy-zoau-from-artifactory/vars/main.yml` and add the line:  
`api_key: {ARTIFACTORY API KEY}`

You can run this playbook by entering the following command in your terminal:  
`ansible-playbook -i inventories/zvm install-zoau.yml`

## Additional Info

### Encrypted Variables: Ansible Vault

Ansible provides variable level encryption using Ansible Vault.

Credentials need to be provided for certain tasks. These include:

* Credentials for firewall authentication
  * `auth_user` and `auth_pass` in `/{{ vars_folder_name }}/auth.yml`
* Ansible Tower password if using _create-inventory.yml_
  * `tower_password` in `/{{ vars_folder_name }}/create-inventory.yml`

Encrypted credentials can be generated from the CLI as follows:

```yaml
$ ansible-vault encrypt_string 'somecredentialhere'                                                                                                                                                        blake.beckeribm.com@blakes-mbp
New Vault password:
Confirm New Vault password:
!vault |
          $ANSIBLE_VAULT;1.1;AES256
          31326134663930356434323835663838316162373662336435303165306235643561346432666431
          6430363966386339373162306261616462383763343432340a663134646338373362323231663061
          35613465326238636363616333386461643630313437313265316135303532346665353433366363
          6362303266353065360a316432653661373736356465316662633833636265313637383637373830
          39353337373966383762386264316337643966643965353330303233393630616637
Encryption successful
```

The output, beginning with `!vault |`  can be used as the values for the variables mentioned above.

Some IDEs will display warnings due to formatting, please ignore the warnings.

If running the playbooks from CLI, the `--ask-vault-pass` flag will need to be specified.
