send-template
=========

Send a single template to z/OS target node.

Requirements
------------

None

Role Variables
--------------

| Variable      | Definition                                           |
| ------------- | ---------------------------------------------------- |
| path          | The path to the template on the Ansible control node |
| uss_file_path | the path where JCL and other scripts will be stored  |

Dependencies
------------

None

Example Playbook
----------------

```yaml
- include_role: 
    name: send-template
  vars:
    path: '{{ playbook_dir }}/templates/step2/COPYACB.j2'

```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)