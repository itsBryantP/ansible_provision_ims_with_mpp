configure-ims-bank
=========

Perform application deployment and configuration for IMS Bank.

Requirements
------------

* pjdd utility from Z Open Automation Utilities.

Role Variables
--------------

| Variable                   | Definition                                                                                                                                                          |
|----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| API_DEPLOY_PATH            | The location to deploy the zCEE API files for IMS Bank.                                                                                                             |
| COBOL_COMP_LIB_HLQ         | The 1st qualifier of the COBOL compiler library to use.                                                                                                             |
| COBOL_IGZCJAVA_PATH        | The path to COBOL compiler java libraries.                                                                                                                          |
| DFS_AUTH_LIB_HLQ1          | The first HLQ to be used to store middleware (IMS, zCEE, etc) related datasets. these include procs, OLDS, WADS, PROCLIB, JOBS, and application specific datasets.  |
| DFS_AUTH_LIB_HLQ2          | The second HLQ to be used to store middleware (IMS, zCEE, etc) related datasets. these include procs, OLDS, WADS, PROCLIB, JOBS, and application specific datasets. |
| DFS_AUTH_LIB_SYSHLQ1       | The first HLQ for system datasets that will be used by IMS or other middleware during provisioning. These should exist prior to running playbooks.                  |
| DFS_AUTH_LIB_SYSHLQ2       | The second HLQ for system datasets that will be used by IMS or other middleware during provisioning. These should exist prior to running playbooks.                 |
| DFS_DS_VOLUME1             | The first volume to use for storage when not using SMS.                                                                                                             |
| DFS_IMSPlex                | The IMS plex name.                                                                                                                                                  |
| DFS_IMS_SSID               | The SSID for the IMS instance to be provisioned.                                                                                                                    |
| DFS_IMS_UNIT               | The installation group name for the device on which the system is to place data sets.                                                                               |
| DFS_SMS_CLASS              | The SMS storage class to use when using SMS.                                                                                                                        |
| IMSBANK_HLQ1               | The 1st qualifier to use for the datasets related to IMS Bank                                                                                                       |
| IMSBANK_HLQ2               | The 2nd qualifier to use for the datasets related to IMS Bank                                                                                                       |
| IMSBANK_SYSHLQ1            | the 1st qualifier for the system datasets containing content used during IMS Bank deployment                                                                        |
| IMSBANK_SYSHLQ2            | the 2nd qualifier for the system datasets containing content used during IMS Bank deployment                                                                        |
| IMSPLEX                    | The name of the IMS plex used by IMS Bank                                                                                                                           |
| INACTIVE_ACBLIB            | The name of the inactive ACBLIB, dynamically overridden during app deployment                                                                                       |
| JOB_CARD                   | The default job card inserted for dynamically generated JCL. ensure MSGCLASS and CLASS are correct for desired environment.                                         |
| PASSWORD                   | The password for zCEE user to use during application deployment                                                                                                     |
| PLI_COMP_HLQ               | 1st qualifier of the PL/I compiler library                                                                                                                          |
| PLI_LIB_HLQ                | The PLI compiler library dataset                                                                                                                                    |
| PROJECT_NAME               | The project name to be used when defining IMS Bank to zCEE                                                                                                          |
| ROUTE                      | The SSID of the IMS instance used by IMS Bank                                                                                                                       |
| SAMPLE_FILE_DIR            | Location to place zCEE sample bank application files.                                                                                                               |
| SERVICE_DEPLOY_PATH        | the location to deploy IMS Bank zCEE project                                                                                                                        |
| TARGET_USERNAME            | The z/OS username to use for job submission.                                                                                                                        |
| USERNAME                   | zCEE login username to use during application deployment                                                                                                            |
| USE_GIT                    | should git be used to retrieve latest IMS Bank configuration                                                                                                        |
| ZCEE_BUILD_TOOLKIT_PATH    | the path to the zCEE build toolkit if already installed                                                                                                             |
| ZCEE_SERVER                | address to zCEE server                                                                                                                                              |
| acblib                     | list of vars with name attribute referencing members of ACBLIB, defaults should be sufficient                                                                       |
| api_key                    | The API key to use for authentication with Artifactory.                                                                                                             |
| datasets                   | list of datasets to create during application deployment, along with their attributes                                                                               |
| dbds                       | list of vars with name attribute referencing members of DBDLIB, defaults should be sufficient                                                                       |
| loads                      | list of datasets and their attributes needed to load IMS Bank data                                                                                                  |
| psbs                       | list of vars with name attribute referencing members of PSBLIB, defaults should be sufficient                                                                       |
| uss_datasets               | folder names used to copy source from USS to MVS, defaults should be sufficient                                                                                     |
| uss_file_path              | the path where JCL and other scripts will be stored                                                                                                                 |
| uss_utilities_path         | where MVSUTILS/MVSCMD and other needed tools/scripts are installed on z/OS target node                                                                              |


  ```yaml
  datasets:
  - name: DBD
    type: library
    primary_size: 2
    secondary_size: 1
    record_format: fb
    logical_record_length: 80
    block_size: 32720
    dir_blocks: 10
  ```

  ```yaml
    uss_datasets:
      - name: PLI
      - name: DBD
      - name: PSB
      - name: JCL
      - name: COBOL 
      - name: LoadData
  ```

```yaml
  loads:
  - name: ACCOUNT
    type: basic
    primary_size: 3
    secondary_size: 1
    record_format: fb
    logical_record_length: 200
    block_size: 27800
```

Dependencies
------------

Roles:

* send-templates
* install-zconbt
* git-clone-private-repo
* check-acblib
* zcee-deploy
* load-csv-data

Example Playbook
----------------

```yaml
  - include_role:
      name: configure-ims-bank
```

License
-------

BSD

Author Information
------------------

Jenny Ha (jenny.ha@ibm.com)
Asif Mahmud (asif.mahmud@ibm.com)