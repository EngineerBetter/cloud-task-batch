#!/bin/bash

set -eu

DATAFLOW_URL=https://dataflow-fil.cfapps.io

curl -X DELETE ${DATAFLOW_URL}/tasks/definitions/task
curl -X DELETE ${DATAFLOW_URL}/apps/task/app
