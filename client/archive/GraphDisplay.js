// import Plot from 'react-plotly.js';
import React, {Component} from 'react';
import BoxWhiskerModal from './BoxWhiskerModal'
import CanvasJSReact from './canvasjs-3.0.5/canvasjs.react';
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;


// -----------------------------------------------------------
// -----------------------------------------------------------
// THIS CLASS IS NO LONGER NEEDED BUT KEPT HERE AS AN ARCHIVE
// -----------------------------------------------------------
// -----------------------------------------------------------

class GraphDisplay extends Component {
    constructor () {
        super();
        // var graphTitle = "Districting Plans";
        this.state = { 
            selectedFilters : null,
            modalOpen : false,
            graphOptions : {
                animationEnabled: true,
                theme: "light2",
                title:{
                    text: "VAP Filter vs. Indexed Districts" // Existing plan v.s probabilistic plan - R. Kelly's words. Existing plan should "overlap" or be compared alongside with these district plans.
                },
                legend:{
                    horizontalAlign: "right",
                    verticalAlign: "top",
                },
                axisY: {
                    title: "Voting Age Population (VAP) by Demographic Filter",
                },
                axisX: {
                    title: "Indexed Districts"
                },
                
                data: [{
                    type: "boxAndWhisker",
                    legendText: "Generated",
                    showInLegend: true,
                    color: "red",
                    upperBoxColor: "#A72A17",
                    lowerBoxColor: "#A3A3A3",
                    yValueFormatString: "###.0# ",
                    dataPoints: [ // indexed districts
                        { label: "1", y: [61, 65, 63.5, 70, 68] },
                        { label: "2", y: [63, 68, 66.5, 76, 72] },
                        { label: "3", y: [65, 71, 69.5, 78, 75] },
                        { label: "4", y: [67, 73, 72, 80, 78] },
                        { label: "5", y: [69, 76, 75, 83, 80] },
                        { label: "6", y: [71, 78, 78,  85, 83] },
                        { label: "7", y: [74, 81, 81, 87, 86] },
                        // For the length of how many districts in state:
                            // Take the job and find min/q1/q2/q3/max of that district (min, for example, is the plan that holds the lowest vap of that district)
                        // When you've calculated for every district, sort and format based on above
                    ]
                },
                {
                    type: "scatter",
                    legendText: "Enacted",
                    showInLegend: true,
                    markerSize: 8,
                    color: "#007BFF",
                    toolTipContent: "District Percentage: {y}",
                    dataPoints: [
                        { x: 0, y: 68},
                        { x: 1, y: 71},
                        { x: 2, y: 73},
                        { x: 3, y: 74},
                        { x: 4, y: 77},
                        { x: 5, y: 80},
                        { x: 6, y: 83},
                    ]
                }]
            },
        }
    }

    toggleBoxWhiskerModal = () => {
        // console.log(this.state.selectedFilters);
        if (this.state.modalOpen == false) this.setState({modalOpen : true});
        else this.setState({modalOpen : false});
    }

    render() {         

        // if(this.state.selectedFilters != this.props.selectedFilters) {
        //     this.setState({selectedFilters : this.props.selectedFilters});
        // }

        // Selected filters would be chosen from the map, and then passed to here. 
        // No need to have a dropdown allowing them to choose from 

        return (
            <div className="graphDisplayWrapper"> 
                <div onClick={this.toggleBoxWhiskerModal} >
                    <div id="plotView1"> 
                        <CanvasJSChart options = {this.state.graphOptions}/>
                    </div>
                </div>
                <div>
                    <BoxWhiskerModal graphOptions={this.state.graphOptions} toggleModal ={this.toggleBoxWhiskerModal} showModal={this.state.modalOpen} />
                </div>
                    < br />
                    < br />
                    < br />
            </div>
        );
    }
}

export default GraphDisplay;
