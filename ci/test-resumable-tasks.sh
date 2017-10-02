#!/bin/bash

set -eux

DATAFLOW_URL=https://dataflow-fil.cfapps.io

launch_task() {
	curl -X POST -d 'name=task' ${DATAFLOW_URL}/tasks/executions
	sleep 15
}

assert_logs_contain() {
	local EXPECTED_MESSAGE=$1
	cf logs task --recent | grep -qE "${EXPECTED_MESSAGE}"
	echo Logs contained expected message: ${EXPECTED_MESSAGE}
}

assert_task_status() {
	local STATUS=$1
	assert_logs_contain "and the following status: \[${STATUS}\]"
}

set_crashed_account() {
	cf set-env task crashedAccountNo 111111
}

unset_crashed_account() {
	cf unset-env task crashedAccountNo
}

launch_task
assert_task_status 'COMPLETED'

set_crashed_account
launch_task
assert_task_status 'FAILED'

unset_crashed_account
launch_task
assert_task_status 'COMPLETED'
assert_logs_contain 'Step already complete or not restartable'
