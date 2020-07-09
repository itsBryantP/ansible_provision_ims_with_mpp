clean-deprovisioned-instances
=========

Remove any templates in Ansible Tower that represent removed instances.

Requirements
------------

None

Role Variables
--------------

| Variable            | Definition                                                                       |
|---------------------|----------------------------------------------------------------------------------|
| inventory_name      | The name of the Ansible Tower inventory used to store instance state information |
| tower_password      | the password for the administrator                                               |
| tower_url           | the URL of Ansible Tower                                                         |
| tower_username      | the administrator username used to perform copy and permissions operations       |

Dependencies
------------

Roles:

* remove-tower-template

Example Playbook
----------------


```yaml
  roles:
    - clean-deprovisioned-instances
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)