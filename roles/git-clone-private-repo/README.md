git-clone-private-repo
=========

This role assists in cloning a private git repo on a target system.

Requirements
------------

An SSH key to clone the private git repository needs to be available to the Ansible control node's SSH agent.

SSH key forwarding is necessary to use this role.
SSH key forwarding can be enabled by setting the option `ForwardAgent=yes` in `ssh-args` inside _ansible.cfg_.

In _ansible.cfg_:
```ini
[ssh_connection]

# ssh arguments to use
# Leaving off ControlPersist will result in poor performance, so use
# paramiko on older platforms rather than removing it, -C controls compression use
ssh_args = -C -o ControlMaster=auto -o ControlPersist=1200s -o PreferredAuthentications=publickey -o ForwardAgent=yes -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null 
```

Role Variables
--------------

| Variable        | Definition                                                                                                                                               |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| CLONE_DEST      | The destination for the cloned repo on the Ansible target's filesystem.                                                                                  |
| GIT_BRANCH      | The branch to checkout after cloning the repository. If the master branch is desired, this variable is not needed.                                       |
| GIT_REPO        | The SSH url of the GitHub repository to be cloned. An example would be ssh://git@github.ibm.com/IBMZSoftware/nazare-demo-imsapp.git                      |
| TARGET_USERNAME | The z/OS username to use for job submission.                                                                                                             |
| secret_key      | if using Ansible Tower, _secret-key_ holds the name of an SSH key living on the Ansible Tower system. If not using Ansible Tower, this should not be set |

Dependencies
------------

None

Example Playbook
----------------

```yaml
  - include_role:
      name: git-clone-private-repo
    when: USE_GIT
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)