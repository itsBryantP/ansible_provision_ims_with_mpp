install-mvs-utilities
=========

Copies and unpacks mvsutils and mvscmd on z/OS Unix interface.

Requirements
------------

Must be run as system administrator

Role Variables
--------------

| Variable           | Definition                                                                             |
|--------------------|----------------------------------------------------------------------------------------|
| uss_utilities_path | where MVSUTILS/MVSCMD and other needed tools/scripts are installed on z/OS target node |

Dependencies
------------

No dependencies

Example Playbook
----------------

```yaml
- hosts: servers
  roles:
    - install-mvs-utilities
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)