create-iefjobs-members
=========

Create a member of IEFJOBS for each procedure in SYS1.PROCLIB
Runs the rexx program to build the IEFJOBS member.

Requirements
------------

Must be run as administrator

Role Variables
--------------

| Variable       | Definition                                                               |
|----------------|--------------------------------------------------------------------------|
| DFS_IMS_SSID   | The SSID for the IMS instance to be provisioned.                         |
| DFS_IMS_USERID | The user ID to use for IMS related provisioning steps.                   |
| DFS_useIEFJOBS | Y or N should IEFJOBS be used                                            |
| zCloud_IEFJOBS | IEFJOBS dataset name                                                     |
| zCloud_PROCLIB | the PROCLIB to store procedures relating to the provisioned IMS instance |

Dependencies
------------

Depends on the following roles:

* submit-tso-rexx
* check-job-status
* save-as-dataset

Example Playbook
----------------

    - hosts: servers
      roles:
         - create-iefjobs-members

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)