


import React, { Component } from 'react';
import Identicon from 'identicon.js';
import box from '../symbol.png'

class Navbar extends Component {

  render() {
    return (
      <nav className="navbar navbar-lightblue bg-dark p-2 text-Montserrat font-size-44px ">
        <a
          className="navbar-brand col-sm-3 col-md-2 mr-0"
          href="https://github.com/newb-dev-1008/Scholastic"
          target="_blank"
          rel="noopener noreferrer"
        >
          <img src={box} width="30" height="30" className="align-top" alt="" />
          <b className='text-black font-size: large' >Reportize </b>
        </a>
        <ul className="navbar-nav px-3">
          <li>
            <small id="account">
              <a target="_blank"
                 alt=""
                 className="text-white"
                 rel="noopener noreferrer"
                 href={"https://etherscan.io/address/" + this.props.account}>
                {this.props.account ? this.props.account.substring(0,6) : '0x0'}...{this.props.account ? this.props.account.substring(38,42) : '0x0'}
              </a>
            </small>
            { this.props.account
              ? <img
                  alt=""
                  className='ml-2'
                  width='30'
                  height='30'
                  src={`data:image/png;base64,${new Identicon(this.props.account, 30).toString()}`}
                />
              : <span></span>
            }
          </li>
        </ul>
      </nav>
    );
  }
}

export default Navbar;