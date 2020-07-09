authenticate-with-firewall
=========

This role is used by Ansible Tower to authenticate with a firewall using telnet.

Requirements
------------

System to authenticate with must be able to receive authentication using telnet.

Role Variables
--------------

| Variable        | Definition                                                                                 |
|-----------------|--------------------------------------------------------------------------------------------|
| auth_pass       | The password used for firewall authentication.                                             |
| auth_uri        | The uri of the firewall. Navigating to this uri with telnet should prompt for credentials. |
| auth_user       | The user to use for firewall authentication.                                               |

Dependencies
------------

None

Example Playbook
----------------

```yaml
  roles:
    - authenticate-with-firewall
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)