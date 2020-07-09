deploy-frontend-cli
=========

Deploy the frontend CLI for use with IMS Bank

Requirements
------------

Any pre-requisites that may not be covered by Ansible itself or the role should be mentioned here. For instance, if the role uses the EC2 module, it may be a good idea to mention in this section that the boto package is required.

Role Variables
--------------

| Variable        | Definition                                                          |
|-----------------|---------------------------------------------------------------------|
| APP_NAME        | The name of the OpenShift application for the frontend website.     |
| AUTH_TOKEN      | The authentication token used to login to OpenShift.                |
| CLI             | The path to the OpenShift CLI binary.                               |
| DOCKER_IMAGE    | The docker image to use for frontend application                    |
| DOCKER_REGISTRY | The docker registry to use for docker images                        |
| SERVER          | Address of OpenShift server on which to deploy frontend application |

Dependencies
------------

A list of other roles hosted on Galaxy should go here, plus any details in regards to parameters that may need to be set for other roles, or variables that are used from other roles.

Example Playbook
----------------

Including an example of how to use your role (for instance, with variables passed in as parameters) is always nice for users too:

    - hosts: servers
      roles:
         - { role: username.rolename, x: 42 }

License
-------

BSD

Author Information
------------------

Asif Mahmud (asif.mahmud@ibm.com)