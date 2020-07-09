zcee-deploy
=========

Deploy IMS Bank application to zCEE.

Requirements
------------

None

Role Variables
--------------

| Variable                                    | Definition                                                      |
|---------------------------------------------|-----------------------------------------------------------------|
| API_DEPLOY_PATH                             | The location to deploy the zCEE API files for IMS Bank.         |
| PASSWORD                                    | The password for zCEE user to use during application deployment |
| PROJECT_NAME                                | The project name to be used when defining IMS Bank to zCEE      |
| SERVICE_DEPLOY_PATH                         | the location to deploy IMS Bank zCEE project                    |
| TARGET_USERNAME                             | The z/OS username to use for job submission.                    |
| USERNAME                                    | zCEE login username to use during application deployment        |
| ZCEE_BUILD_TOOLKIT_PATH                     | the path to the zCEE build toolkit if already installed         |
| ZCEE_PORT                                   | port used by zCEE server                                        |
| ZCEE_SERVER                                 | address to zCEE server                                          |
| uss_file_path                               | the path where JCL and other scripts will be stored             |

Dependencies
------------

None

Example Playbook
----------------

```yaml
- include_role:
    name: zcee-deploy
```

License
-------

BSD

Author Information
------------------

An optional section for the role authors to include contact information, or a website (HTML is not allowed).
