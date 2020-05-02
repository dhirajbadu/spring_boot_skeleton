console.log("chart js");


const build = data => {
    const svg = d3.select('#chart');

    const width = +svg.attr("width");
    const height = +svg.attr("height");

    data.forEach(d => {
        console.log(d.country);
        d.population = +d.population * 100;
    });

    const xValue = d => d.population;
    const yValue = d => d.country;
    const color = d => d.color;


    const margin = {top: 20, right: 30, bottom: 30, left: 20};
    const innerWidth = width - margin.right - margin.left;
    const innerHeight = height - margin.top - margin.bottom;

    const xScale = d3.scaleLinear()
        .domain([0, d3.max(data, xValue)])
        .range([0, innerWidth]);

    console.log(xScale.domain());
    console.log(xScale.range());

    const tickFormatXAxix = num => d3.format('.2s')(num);
    const xAxis = d3.axisBottom(xScale)
        .tickFormat(tickFormatXAxix);

    const yScale = d3.scaleBand()
        .domain(data.map(yValue))
        .range([0, innerHeight])
        .padding(0.1);

    console.log(yScale.domain());
    console.log(yScale.range());

    const yAxis = d3.axisLeft(yScale);


    const g = svg.append('g')
        .attr('transform', 'translate(' + margin.left + ' , ' + margin.top + ')');

    g.append('g').call(yAxis);
    g.append('g').call(xAxis)
        .attr('transform', 'translate(' + 0 + ' , ' + innerHeight + ')');

    g.selectAll('rect')
        .data(data)
        .enter()
        .append('rect')
        .attr('y', d => yScale(yValue(d)))
        .attr('width', d => xScale(xValue(d)))
        .attr('height', yScale.bandwidth())
        .attr('fill', color);
};


const rawData = [{'country': 'a', 'population': 123, 'color' : 'steelblue'}, {'country': 'b', 'population': 231, 'color' : 'red'}, {
    'country': 'c',
    'population': 543,
    'color' : 'yellow'
}, {'country': 'd', 'population': 12, 'color' : 'green'}];
build(rawData);