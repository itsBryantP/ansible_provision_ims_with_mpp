deploy-zoau-from-artifactory
=========

This role can be used to deploy Rocket Software's python36 to a z/OS system.

Requirements
------------

* tar and gzip must be present on the z/OS target system

Role Variables
--------------

| Variable           | Definition                                                                             |
|--------------------|----------------------------------------------------------------------------------------|
| api_key            | The API key to use for authentication with Artifactory.                                |
| bash               | The path to bash on z/OS target system.                                                |
| uss_utilities_path | where MVSUTILS/MVSCMD and other needed tools/scripts are installed on z/OS target node |
| zoau_file_name     | the name the zoau pax file should be given after download                              |

Dependencies
------------

None

Example Playbook
----------------

```yaml
  tasks:
    - include_role:
        name: deploy-zoau-from-artifactory
```

License
-------

BSD

Author Information
------------------

Omar Elbarmawi (omar@ibm.com)
Jerry Li (lij@us.ibm.com)