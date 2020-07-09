deploy-python36-from-artifactory
=========

This role can be used to deploy Rocket Software's python36 to a z/OS system.

Requirements
------------

* curl must be available on Ansible controller and z/OS target system.
* tar and gzip must be present on the z/OS target system
* 
Role Variables
--------------

| Variable            | Definition                                                                                                                     |
|---------------------|--------------------------------------------------------------------------------------------------------------------------------|
| api_key             | The API key to use for authentication with Artifactory.                                                                        |
| copy_dir            | The directory on the z/OS target system to place the compressed python install archive.                                        |
| curl                | The path to curl on z/OS target system.                                                                                        |
| gzip                | The path to gzip on z/OS target system                                                                                         |
| install_dir         | The directory on the z/OS target system to decompress and extract the compressed python install archive                        |
| python_file_name    | The name of the compressed python install archive placed in the _files_ folder of the role. Do not include the file extension. |
| python_release_name | The base name for the archive stored in the _files_ folder of the role. (python)                                               |
| python_release_type | The release version of python ( i.e. py36 ). Most likely the end of python_file_name after the final hyphen                    |

Dependencies
------------

None

Example Playbook
----------------

```yaml
  tasks:
    - include_role:
        name: deploy-python36-from-artifactory
```

License
-------

BSD

Author Information
------------------

Omar Elbarmawi (omar@ibm.com)
Jerry Li (lij@us.ibm.com)