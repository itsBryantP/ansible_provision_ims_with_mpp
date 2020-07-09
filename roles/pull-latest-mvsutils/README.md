pull-latest-mvsutils
=========

This role performs the following tasks:

1. Get latest build number for MVSUtil
2. Build route to file in Artifactory based on build number
3. Download and extract file

Requirements
------------

Any pre-requisites that may not be covered by Ansible itself or the role should be mentioned here. For instance, if the role uses the EC2 module, it may be a good idea to mention in this section that the boto package is required.

Role Variables
--------------

| Variable                                                                                     | Definition                                                                             |
|----------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------|
| api_key                                                                                      | The API key to use for authentication with Artifactory.                                |
| mvsutil_version                                                                              | The current version of MVSUtil, automatically parsed from latest artifactory build.    |
| uss_utilities_path                                                                           | where MVSUTILS/MVSCMD and other needed tools/scripts are installed on z/OS target node |

Dependencies
------------

A list of other roles hosted on Galaxy should go here, plus any details in regards to parameters that may need to be set for other roles, or variables that are used from other roles.

Example Playbook
----------------

```yaml
- include_role:
    name: pull-latest-mvsutils
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)