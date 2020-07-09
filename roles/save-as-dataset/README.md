save-as-dataset
=========

Copies file from USS to z/OS dataset. By default, the file to copy is assumed to be at `uss_file_path`.

Requirements
------------

Must be run as system administrator

Role Variables
--------------

| Variable           | Definition                                                                             |
| ------------------ | -------------------------------------------------------------------------------------- |
| dataset_member     | The data set member name used to store our USS file.                                   |
| dataset_pds        | The data set name of the PDS used to store file contents.                              |
| file_name          | The name of the file on the z/OS target host to save to dataset                        |
| uss_utilities_path | where MVSUTILS/MVSCMD and other needed tools/scripts are installed on z/OS target node |

Dependencies
------------

No dependencies

Example Playbook
----------------

```yaml
- name: Save generate JCL as a dataset
  include_role:
    name: save-as-dataset
  vars:
    file_name: 'DFSLDCAT.jcl'
    dataset_pds: '{{ DFS_AUTH_LIB_HLQ1 }}.{{ DFS_AUTH_LIB_HLQ2 }}.INSTALL'
    dataset_member: 'IV3E319K'
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)