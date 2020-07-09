remove-tower-template
=========

A brief description of the role goes here.

Requirements
------------

Any pre-requisites that may not be covered by Ansible itself or the role should be mentioned here. For instance, if the role uses the EC2 module, it may be a good idea to mention in this section that the boto package is required.

Role Variables
--------------

| Variable       | Definition                                                                                                                                                                                                                                     |
|----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| inventory_name | The name of the Ansible Tower inventory used to store instance state information                                                                                                                                                               |
| tower_password | the password for the administrator                                                                                                                                                                                                             |
| tower_url      | the URL of Ansible Tower                                                                                                                                                                                                                       |
| tower_username | the administrator username used to perform copy and permissions operations                                                                                                                                                                     |
| workflow_name  | The name of the Ansible Tower workflow template that should be copied to represent existing instance. In general, the workflow template should perform de-provision of the provisioned instance, then remove itself through some other method. |

Dependencies
------------

A list of other roles hosted on Galaxy should go here, plus any details in regards to parameters that may need to be set for other roles, or variables that are used from other roles.

Example Playbook
----------------

```yaml
- include_role:
    name: remove-tower-template
  vars:
    workflow_name: '{{ item }}'
  loop: '{{ templates_to_remove }}'
  ignore_errors: yes
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)