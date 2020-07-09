create-stateful-workflow
=========

This role makes a copy of a deprovision workflow in Ansible Tower.
This role should run at the end of a provision workflow.

The workflow copy will be assigned a name indicating who and when the provisioned instance was created.
The workflow copy may be used as a record to identify currently provisioned instances.
After running the copied deprovision workflow, the copied workflow will be deleted.

Requirements
------------

None

Role Variables
--------------

| Variable                  | Definition                                                                 |
|---------------------------|----------------------------------------------------------------------------|
| provisioned_instance_name | An identifier to represent the specific instance that was provisioned      |
| tower_password            | the password for the administrator                                         |
| tower_url                 | the URL of Ansible Tower                                                   |
| tower_user_id             | the ID of the user who ran the provision workflow                          |
| tower_username            | the administrator username used to perform copy and permissions operations |

Dependencies
------------

A list of other roles hosted on Galaxy should go here, plus any details in regards to parameters that may need to be set for other roles, or variables that are used from other roles.

Example Playbook
----------------

```yaml
  roles:
    - create-stateful-workflow
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)