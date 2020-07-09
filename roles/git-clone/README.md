Git Clone from Private Repository
=========
An Ansible role that can be used to clone a private GitHub repository. 


Requirements
------------
* This role requires a GitHub personal access token. Generate a new token (Settings -> Developer Settings -> Personal Acess Token). Make sure the token has the scopes outlined in the "Role Variables" section below.
* Encrypt the token by running the following command **in the same directory as the password file**
    
    `ansible-vault encrypt_string '<token>' --name 'GITHUB_ACCESS_TOKEN'`

* Use the generated encrypted string and paste it in the vars/main.yml file

Role Variables
--------------

| Variable            | Definition                                                                                                                                 |
| ------------------- | ------------------------------------------------------------------------------------------------------------------------------------------ |
| CLONE_DEST          | The destination for the cloned repo on the Ansible target's filesystem.                                                                    |
| GITHUB_ACCESS_TOKEN | The personal access token associated with the GitHub account being used. Should have access to repo, admin:public_key, user, admin:gpg_ley |
| GIT_BRANCH          | The branch to checkout after cloning the repository. If the master branch is desired, this variable is not needed.                         |
| GIT_EXECUTABLE      | The location of the git binary on the target machine. For example /usr/bin/git                                                             |
| GIT_REPO            | The SSH url of the GitHub repository to be cloned. An example would be ssh://git@github.ibm.com/IBMZSoftware/nazare-demo-imsapp.git        |
| KEY_PATH            | The Full path of the directory where the SSH key should be stored. A typical location would be /.ssh/<key_name>                            |
| KEY_TITLE           | The title of the SSH key to be added to the GitHub account                                                                                 |
| KNOWN_HOSTS_PATH    | Location of the SSH known_hosts file on the target machine. A typical location would be /.ssh/known_hosts                                  |
| SSH_CONF_PATH       |                                                                                                                                            |
| TARGET_USERNAME     | The z/OS username to use for job submission.                                                                                               |

Example Playbook
----------------
    - hosts: servers
      roles:
         - git-clone

License
-------

BSD

Author Information
------------------

Author: Asif Mahmud

Email: asif.mahmud@ibm.com
