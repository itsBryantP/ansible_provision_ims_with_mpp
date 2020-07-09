check-job-status
=========

Get the maximum return code of a batch job. If higher than the specified maximum passing return code, fail.

If a final return code cannot be found, the role will wait n seconds (2 by default) and check again in case the job has not finished. This will repeat x times (default 60).

Requirements
------------

Must be run as administrator

Role Variables
--------------

| Variable           | Definition                                                                             |
|--------------------|----------------------------------------------------------------------------------------|
| job_id             | The ID of the job to check the output of                                               |
| uss_utilities_path | where MVSUTILS/MVSCMD and other needed tools/scripts are installed on z/OS target node |

Dependencies
------------

`MVSUTILS` command `pjdd` must be installed on the system. `MVSCMD` must also be installed in the same directory as `MVSUTILS`.

Example Playbook
----------------

    - name: Check that job returned successfully
      include_role:
        name: check-job-status
        public: yes
      vars:
        job_id: "{{ command_result.stdout }}"
        max_rc: "0000"

License
-------

BSD

Author Information
------------------

Blake Becker (blake.becker@ibm.com)