install-zconbt
=========

Install z/OS Connect EE build toolkit

Requirements
------------

* The zconbt _tar.bz2_ compressed archive needs to be obtained and placed in this role's _files_ directory.
* Bzip2 is also required on the z/OS target system

Role Variables
--------------

| Variable               | Definition                                                                          |
|------------------------|-------------------------------------------------------------------------------------|
| install_path           | The path on the z/OS target system to install zconbt                                |
| utility_path           | the path on the z/OS target system for the directory containing bzip2               |
| zconbt_compressed_name | the name of the compressed archive for zconbt located in this role's _files_ folder |
  
Dependencies
------------

None

Example Playbook
----------------

```yaml
  - include_role:
      name: install-zconbt
    vars:
      install_path: /u/oeusr01/zconbt
      utility_path: /u/oeusr01/utilities
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)