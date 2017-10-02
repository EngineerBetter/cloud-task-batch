# cloud-task-batch

Example project to spike usage of Spring Cloud Data Flow.

## Resumable tasks

`ci/run-test.sh` demonstrates that a task with a job that fails due to an exception is resumable - the failed step is rolled back, and the one that already succeeded is not repeated.

To run on Pivotal Web Services:

```
	CF_ORG=org \
	CF_SPACE=space \
	CF_USERNAME=username \
	CF_PASSWORD= \
	 ./run-test.sh
```

This uses a paid-for MySQL plan.