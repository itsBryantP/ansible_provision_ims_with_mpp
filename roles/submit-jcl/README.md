submit-jcl
=========

Submit a JCL file from z/OS USS. Returns job ID in variable `command_result.stdout`.

Requirements
------------

None

Role Variables
--------------

| Variable       | Definition                                                                 |
| -------------- | -------------------------------------------------------------------------- |
| jcl_name       | The name of the JCL to submit                                              |
| uss_file_path  | the path where JCL and other scripts will be stored                        |
| command_result | This variable is set by the role itself. Contains Job ID of submitted job. |

Dependencies
------------

None

Example Playbook
----------------

```yaml
- name: Check ACB Lib
  include_role:
    name: submit-jcl
    public: yes
  vars:
    jcl_name: 'CHECKACBLIB.j2'  

- name: Get the logs from the job
  shell: "./pjdd {{ command_result.stdout }} sysprint"
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)