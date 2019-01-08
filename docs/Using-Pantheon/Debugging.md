description: Frequently asked questions FAQ and answers for troubleshooting Pantheon use
<!--- END of page meta data -->

# Debugging Pantheon

## My command line options are not working as I expected? 

Ensure quotes have not been automatically converted to smart quotes or hyphens combined if copying and pasting. 

## How can I monitor node performance and connectivity? 

Using the [`debug_metrics`](../Reference/JSON-RPC-API-Methods.md#debug_metrics) JSON-RPC API method.

## Can I monitor node performance using Prometheus?

You can monitor node performance using the [Prometheus](https://prometheus.io/) monitoring and alerting service.

Use the Pantheon CLI option [`--metrics-enabled`](../Reference/Pantheon-CLI-Syntax.md#metrics-enabled) to allow
Prometheus to access Pantheon client metrics.

Specify the host and port on which Prometheus accesses Pantheon client data using the CLI option
[`--metrics-listen`](../Reference/Pantheon-CLI-Syntax.md#metrics-listen). The default host and port are 127.0.0.1:9545.

When Prometheus is running on your system, it can consume the Pantheon client data directly for monitoring.
No additional configuration is needed for alerting, as Prometheus handles that directly from the data feed.
You can also visualize the collected data using [Grafana](https://grafana.com/).
