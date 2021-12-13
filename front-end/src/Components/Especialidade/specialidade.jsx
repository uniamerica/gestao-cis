import React from "react";
import ReactDOM from "react-dom";
import axios from "axios";

class App extends React.Component {
  state = {
    data: [],
    loading: false,
    error: null
  };

  componentDidMount() {
    const event = { registrations: [1, 2] };
    this.getRegistrations(event);
  }

  getRegistrations = async event => {
    this.setState({
      loading: true
    });

    const promises = event.registrations.map(registration => {
      return axios
        .get("https://jsonplaceholder.typicode.com/comments", {
          params: {
            postId: registration
          }
        })
        .then(({ data }) => {
          return data;
        });
    });

    const registrations = await Promise.all(promises)
      .then(values => {
        return values;
      })
      .catch(error => {
        this.setState({
          loading: false,
          error
        });
      });

    this.setState({
      loading: false,
      data: registrations.flat()
    });
  };

  render() {
    const { data, loading, error } = this.state;
    if (loading) {
      return "loading ....";
    }
    if (error) {
      return "something was wrong";
    }
    return data.map(({ id, name }) => {
      return <div key={id}>name: {name}</div>;
    });
  }
}

const rootElement = document.getElementById("root");
ReactDOM.render(<App />, rootElement);
