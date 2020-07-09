load-csv-data
=========

Import a CSV file into DB2.

Requirements
------------

Any pre-requisites that may not be covered by Ansible itself or the role should be mentioned here. For instance, if the role uses the EC2 module, it may be a good idea to mention in this section that the boto package is required.

Role Variables
--------------

| Variable        | Definition                                                     |
|-----------------|----------------------------------------------------------------|
| CSV_FILE_PATH   | The path to the CSV file containing information for DB2 table. |
| DB2_INSTANCE    | The DB2 instance to target.                                    |
| DB2_PASSWORD    | The password for the DB2 instance.                             |
| DB2_PORT        | The port for DB2 instance.                                     |
| DB2_SCHEMA      | The schema to use for DB2.                                     |
| DB2_TABLE       | The table to use with DB2.                                     |
| DB2_URL         | The URL to DB2 instance.                                       |
| DB2_USER        | The username to use for DB2.                                   |
| PROJECT_NAME    | The project name to be used when defining IMS Bank to zCEE     |
| TARGET_USERNAME | The z/OS username to use for job submission.                   |
| uss_file_path   | the path where JCL and other scripts will be stored            |

Dependencies
------------

None

Example Playbook
----------------

```yaml
- include_role:
      name: load-csv-data
```

License
-------

BSD

Author Information
------------------

An optional section for the role authors to include contact information, or a website (HTML is not allowed).
