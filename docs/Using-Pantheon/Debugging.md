description: Frequently asked questions FAQ and answers for troubleshooting Pantheon use
<!--- END of page meta data -->

# Debugging Pantheon

## Command Line Options Not Working as Expected

Characters such as smart quotes and long (em) hyphens won't work in Pantheon options. Ensure that quotes have
not been automatically converted to smart quotes or that double hyphens have not been combined into em hyphens.

## Monitor Node Performance and Connectivity Using the JSON-RPC API

You can monitor node performance using the [`debug_metrics`](../Reference/JSON-RPC-API-Methods.md#debug_metrics)
JSON-RPC API method.

## Monitor Node Performance Using Third-Party Clients

You can monitor node performance using the [Prometheus](https://prometheus.io/) monitoring and alerting service.
You can also visualize the collected data using [Grafana](https://grafana.com/).

Use the Pantheon CLI option [`--metrics-enabled`](../Reference/Pantheon-CLI-Syntax.md#metrics-enabled) to allow
Prometheus to access Pantheon client metrics.

While Prometheus is running on your system, it can consume the Pantheon client data directly for monitoring.
Specify the host and port on which Prometheus accesses Pantheon client data using the CLI option
[`--metrics-listen`](../Reference/Pantheon-CLI-Syntax.md#metrics-listen). The default host and port are 127.0.0.1:9545.

You can install other Prometheus components such as the Alert Manager. Once you install these components, you don't need to
do additional configuration, because Prometheus handles and analyzes data directly from the feed.

Here's an example of how you can set up and run Prometheus with Pantheon:

* Install the [prometheus main component](https://prometheus.io/download/) for the Prometheus monitoring system and
time series database on your system.

* Navigate to the directory in which you installed the Pantheon binary and run the command with the metrics options;
for example:

    ```bash
    bin/pantheon --dev-mode --network-id="2018" --miner-enabled --miner-coinbase fe3b557e8fb62b89f4916b721be55ceb828dbd73
    --rpc-cors-origins "all" --ws-enabled --rpc-enabled --metrics-enabled
    ```

* In another terminal, run Prometheus.

* Open a web browser to `http://localhost:9090` to view the Prometheus graphical interface.

* Choose **Graph** from the menu bar, then click the **Console** tab below.

* From the **Insert metric at cursor** drop-down, select a metric such as `pantheon_blockchain_difficulty_total` or
`pantheon_blockchain_height`, then click **Execute**. The values are displayed below.

* Click the **Graph** tab to view the data as a time-based graph. The query string is displayed below the graph;
for example: `{pantheon_blockchain_height{instance="localhost:9545",job="prometheus"}`
