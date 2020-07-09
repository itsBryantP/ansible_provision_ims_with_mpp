check-acblib
=========

Check which ACB library is inactive

Requirements
------------

* pjdd utility from Z Open Automation Utilities

Role Variables
--------------

| Variable           | Definition                                                                             |
|--------------------|----------------------------------------------------------------------------------------|
| IMSBANK_HLQ1       | The 1st qualifier to use for the datasets related to IMS Bank                          |
| IMSBANK_HLQ2       | The 2nd qualifier to use for the datasets related to IMS Bank                          |
| INACTIVE_ACBLIB    | The name of the inactive ACBLIB, dynamically overridden during app deployment          |
| uss_utilities_path | where MVSUTILS/MVSCMD and other needed tools/scripts are installed on z/OS target node |

Dependencies
------------

Roles:

* submit-jcl

Example Playbook
----------------

```yaml
    - include_role:
        name: check-acblib
```

License
-------

BSD

Author Information
------------------

Jenny Ha (jenny.ha@ibm.com)