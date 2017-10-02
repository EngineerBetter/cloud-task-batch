#!/bin/bash

set -eu

./deploy-dataflow-server.sh
./setup-task.sh
./failed-task-is-resumable.sh
./teardown-task.sh
./teardown-dataflow-server.sh
