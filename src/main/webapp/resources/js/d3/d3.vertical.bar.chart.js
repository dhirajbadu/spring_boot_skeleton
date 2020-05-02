
const vBarChart = data =>{

    const svg = d3.select('#chart1');

    const width = +svg.attr("width");
    const height = +svg.attr("height");

    const margin = {top: 20, right: 30, bottom: 30, left: 30};
    const innerWidth = width - margin.right - margin.left;
    const innerHeight = height - margin.top - margin.bottom;

    data.forEach(d => {
        console.log(d.country);
        d.population = +d.population * 100;
    });

    const yValue = d => d.population;
    const xValue = d => d.country;

    const yScale = d3.scaleLinear()
        .domain([0, d3.max(data, yValue)])
        .range([innerHeight, 0]);

    console.log(yScale.domain());
    console.log(yScale.range());

    const tickFormatYAxix = num => d3.format('.2s')(num);
    //const yAxis = d3.axisLeft(yScale)
      //  .tickFormat(tickFormatYAxix);

    const xScale = d3.scaleBand()
        .domain(data.map(xValue))
        .range([margin.left + 5, innerWidth]);

    console.log(xScale.domain());
    console.log(xScale.range());

    //const xAxis = d3.axisBottom(xScale);

    //const g = svg.append('g').attr('transform', 'translate(' + margin.left + ' , ' + margin.bottom + ')');

    //g.append('g').call(yAxis)

    svg.selectAll('rect')
        .data(data)
        .enter()
        .append('rect')
        .attr("x" , d => xScale(xValue(d)))
        .attr("y" , d => yScale(yValue(d)))
        .attr('width', 30)
        .attr('height', d => yScale(yValue(d)))
        .attr('fill' , 'steelblue');

};

const rawData = [{'country': 'a', 'population': 123, 'color' : 'steelblue'}, {'country': 'b', 'population': 231, 'color' : 'red'}, {
    'country': 'c',
    'population': 543,
    'color' : 'yellow'
}, {'country': 'd', 'population': 12, 'color' : 'green'}];
vBarChart(rawData);