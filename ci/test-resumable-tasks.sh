#!/bin/bash

set -eux

DATAFLOW_URL=https://dataflow-fil.cfapps.io

launch_task() {
	curl -X POST -d 'name=task' ${DATAFLOW_URL}/tasks/executions
}

assert_task_status() {
	local STATUS=$1
	local EXPECTED_MESSAGE="and the following status: \[${STATUS}\]"
	cf logs task --recent | grep -qE "${EXPECTED_MESSAGE}"
	echo Task completed with status in logs of ${STATUS}
}

set_crashed_account() {
	cf set-env dataflow-server crashedAccountNo 111111
}

launch_task
sleep 15
assert_task_status 'COMPLETED'

set_crashed_account
launch_task
sleep 15
assert_task_status 'FAILED'
#unset env var
#launch_task
#assert_task_completed 'COMPLETED'
#check that it did not do all the records
