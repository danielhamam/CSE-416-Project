import React, {Component} from 'react';
import RangeSlider from 'react-bootstrap-range-slider';
import {Form} from 'react-bootstrap';
import Select from 'react-select';
import makeAnimated from 'react-select/animated';

class InputsBatch extends Component {
    constructor () {
        super();
        this.state = {
            plansAmount : 0,
            compactnessAmount : 0,
            populationDifference : 0
            // plansChangeAmount : ""
            // slideStop={this.changeValue}

        }
    }

    // To change the amount in the slider
    changePlanAmount = (e) => {
        this.setState({plansAmount : e.target.value});
    }

    changeCompactnessAmount = (e) => {
        this.setState({compactnessAmount : e.target.value});
    }

    changePopulationDifference = (e) => {
        this.setState({populationDifference: e.target.value});
    }

    render() {

        const componentsAnimation = makeAnimated();
        const options = [
            {
              label: "Ethnicity",
              options: [
                { label:"White", value: "white"},
                { label:"African American", value: "africanamerican"},
                { label:"Latino", value: "latino"},
                { label:"Asian", value: "asian"},
                { label:"American Indian", value: "americanindian"},
                { label:"Hawaiian", value: "hawaiian"},
                { label:"Other", value: "other"},
              ]},
          ];

        return (
            <Form> 
                <div className="form-group">

                {/* --------------------------------------- */}
                {/*     NUMBER OF DISTRICT PLANS SLIDER  */}
                {/* --------------------------------------- */}

                    <label for="exampleInputEmail1"> Districting Plans (#): </label>
                    <div className="row"> 
                        <div className="col-4">
                            <Form.Control size="sm" value={this.state.plansAmount} onChange={this.changePlanAmount}/>
                        </div>
                        <div className="col-8">
                            <div className="rangeSliderContainer"> 
                                <RangeSlider className="rangeSlider" disabled={false} onChange={this.changePlanAmount} step={1} min={0} max={5000} tooltip='auto' value={this.state.plansAmount} />
                            </div>
                        </div>
                    </div>
                    <small className="form-text text-muted">Enter the amount of district plans you would like to generate. The current limit is 5000. </small>

                < br/>
                {/* --------------------------------------- */}
                {/*     COMPACTNESS MEASURE SLIDER          */}
                {/* --------------------------------------- */}

                <label for="exampleInputEmail1"> Compactness (%): </label>
                    <div className="row"> 
                        <div className="col-4">
                            <Form.Control size="sm" value={this.state.compactnessAmount} onChange={this.changeCompactnessAmount}/>
                        </div>
                        <div className="col-8">
                            <div className="rangeSliderContainer"> 
                                <RangeSlider className="rangeSlider" disabled={false} onChange={this.changeCompactnessAmount} step={0.01} min={0} max={1} tooltip='auto' value={this.state.compactnessAmount} />
                            </div>
                        </div>
                    </div>
                    <small className="form-text text-muted"> Enter the compactness preference for your district plans. </small>
                </div>

                {/* --------------------------------------- */}
                {/*     POPULATION DIFFERENCE SLIDER        */}
                {/* --------------------------------------- */}

                <label for="exampleInputEmail1"> Population Difference Limit (%): </label>
                    <div className="row"> 
                        <div className="col-4">
                            <Form.Control size="sm" value={this.state.populationDifference} onChange={this.changePopulationDifference}/>
                        </div>
                        <div className="col-8">
                            <div className="rangeSliderContainer"> 
                                <RangeSlider className="rangeSlider" disabled={false} onChange={this.changePopulationDifference} step={0.1} min={0} max={1.7} tooltip='auto' value={this.state.populationDifference} />
                            </div>
                        </div>
                    </div>
                    <small className="form-text text-muted"> Enter the limit as measured by the difference between the most populous district and the least populous district. </small>
                    <br /> 

                {/* --------------------------------------- */}
                {/*        MINORITIES TO BE ANALYZED        */}
                {/* --------------------------------------- */}

                <label > Minority Focus Group(s): </label>

                    <div className="minorityFocusStyle">
                        <Select isSearchable={true} placeholder="Minority group(s)" components={componentsAnimation} className="basic-multi-select" options={options} isMulti={true} />
                    </div>
                    <small className="form-text text-muted"> Select the minority group(s) from the dropdown to particularly analyze. </small>

                <br />

                {/* --------------------------------------- */}
                {/*              BATCH NAME                 */}
                {/* --------------------------------------- */}

                    <div className="form-group">
                    <label >Batch Name:</label>
                    <div className="customBatchNameContainer"> <input className="input-normal form-control" maxlength={11} placeholder="Custom Batch Name"/> </div> 
                </div>
                <br /> 

                {/* --------------------------------------- */}
                {/*            GENERATE BUTTON              */}
                {/* --------------------------------------- */}

                    <button type="submit" className="btn btn-primary">Generate</button>
            </Form>
        );
    }
}

export default InputsBatch;