provision-mpp
=========

Provision an MPP region for IMS.

Requirements
------------

None

Role Variables
--------------

| Variable                       | Definition                                                                                                                                                          |
|--------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| DB2_SSM                        | The DB2 subsystem member (SSM).                                                                                                                                     |
| DFSMPR_NAME                    | The name/identifier to use for the MPP region                                                                                                                       |
| DFS_AUTH_LIB_HLQ1              | The first HLQ to be used to store middleware (IMS, zCEE, etc) related datasets. these include procs, OLDS, WADS, PROCLIB, JOBS, and application specific datasets.  |
| DFS_AUTH_LIB_HLQ2              | The second HLQ to be used to store middleware (IMS, zCEE, etc) related datasets. these include procs, OLDS, WADS, PROCLIB, JOBS, and application specific datasets. |
| DFS_AUTH_LIB_SYSHLQ1           | The first HLQ for system datasets that will be used by IMS or other middleware during provisioning. These should exist prior to running playbooks.                  |
| DFS_AUTH_LIB_SYSHLQ2           | The second HLQ for system datasets that will be used by IMS or other middleware during provisioning. These should exist prior to running playbooks.                 |
| DFS_IMS_SSID                   | The SSID for the IMS instance to be provisioned.                                                                                                                    |
| DFS_IMS_SYSAFF                 | The system eligible to run our jobs, optional.                                                                                                                      |
| DFS_IMS_USERID                 | The user ID to use for IMS related provisioning steps.                                                                                                              |
| DFS_JOBLIB                     | The fully qualified dataset name for the JOBS PDS. For example, this will hold jobs for starting and stoppng IMS resources.                                         |
| DFS_PROCLIB                    | The fully qualified dataset name for the IMS PROCLIB.                                                                                                               |
| DFS_appname                    | The IMS application program name that will be used during the create program step of the playbook. This name will also be used as the IMS PSB Name.                 |
| DFS_traname                    | The transaction name used for IMS bank application                                                                                                                  |
| DFS_tranclass1                 | The first transaction class used by IMS bank application. Likely should be the same as DFS_tranclass                                                                |
| DFS_tranclass2                 | The second transaction class used by IMS bank application.                                                                                                          |
| DFS_tranclass3                 | The third transaction class used by IMS bank application.                                                                                                           |
| DFS_tranclass4                 | The fourth transaction class used by IMS bank application.                                                                                                          |
| JOB_CARD                       | The default job card inserted for dynamically generated JCL. ensure MSGCLASS and CLASS are correct for desired environment.                                         |
| REGION_NUM                     |                                                                                                                                                                     |
| REPLACE_MEMBERS                |                                                                                                                                                                     |
| SEND_CUSTOM_DFSMPR             |                                                                                                                                                                     |
| TSO_USERID                     | TSO USERID for executing the environment setup                                                                                                                      |
| USE_PSEUDO_WFI                 |                                                                                                                                                                     |
| WITH_DB2                       |                                                                                                                                                                     |
| ZOS_CSSLIB                     | Specifies the name of the CSSLIB data set.                                                                                                                          |
| ZOS_LERUNLIB                   | Data set name used as Language Environment runtime library                                                                                                          |
| ZOS_MACLIB                     |                                                                                                                                                                     |
| uss_file_path                  | the path where JCL and other scripts will be stored                                                                                                                 |
| uss_utilities_path             | where MVSUTILS/MVSCMD and other needed tools/scripts are installed on z/OS target node                                                                              |
| zCloud_CSSLIB                  | Dataset containing zos CSS library                                                                                                                                  |
| zCloud_LERuntime               | Data set name used as Language Environment runtime library                                                                                                          |
| zCloud_MACLIB                  | Dataset containing zos macro library                                                                                                                                |

Dependencies
------------

A list of other roles hosted on Galaxy should go here, plus any details in regards to parameters that may need to be set for other roles, or variables that are used from other roles.

Example Playbook
----------------

```yaml
- include_role:
    name: provision-mpp
```

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)