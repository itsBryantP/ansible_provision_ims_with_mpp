flag-template-for-removal
=========

This role is used to flag an Ansible Tower template for removal by a scheduled cleanup job.

Requirements
------------

None

Role Variables
--------------

* inventory_name - the name of the Ansible Tower inventory holding template information. Removal flagging takes place by editing the inventories contents.
* template_name - the name of the Ansible Tower template which should be flagged for removal
* tower_username - the username for an Ansible Tower user with administrative privileges
* tower_password - the password for the Ansible Tower user
* tower_url -  the URL of the Ansible Tower instance


Dependencies
------------

None

Example Playbook
----------------

```yaml
  roles:
    - flag-template-for-removal
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)