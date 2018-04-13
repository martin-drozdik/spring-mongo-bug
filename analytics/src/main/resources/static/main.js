function assert(condition, message) {
    if (!condition) {
        throw message || "Assertion failed";
    }
}

var jsonToTable = function(values, tableName)
{
   var table = $('<table>');
   for(var key in values)
   {
       var row = $('<tr>');
       row.append($('<td>').text(key));
       row.append($('<td>').text(values[key]));
       table.append(row);
   }
   var element = $(tableName);
   assert(element.length);
   element.append(table);
}

var plotTimeSeries = function(timeSeries, graphName)
{
    let traces = []
    for (let key in timeSeries)
    {
        let x = [];
        let y = [];
        let series = timeSeries[key];
        for (let i = 0; i < series.length; ++i)
        {
            x.push(i)
            y.push(series[i])
        }
        traces.push(
            {
                name: key,
                x: x,
                y: y,
                type: 'scatter',
                line: {shape: 'spline'},
            });
    }
    Plotly.newPlot(graphName, traces);
}


$(function()
{
    $.get("api/correctness/all", function(data)
    {
        jsonToTable(data.values, "#correctness_table")
        plotTimeSeries(data.timeSeries, "graph")
    });
});
