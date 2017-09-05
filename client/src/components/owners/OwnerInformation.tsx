import * as React from 'react';

import { Link } from 'react-router';
import { IOwner } from '../../types';

export default ({owner}: { owner: IOwner }) => (
  <section>
    <h2>Owner Information</h2>

    <table className='table table-striped'>
      <tbody>
        <tr>
          <th>Name</th>
          <td id='ownerName'><b>{owner.firstName} {owner.lastName}</b></td>
        </tr>
        <tr>
          <th>Address</th>
          <td id='ownerAddress'>{owner.address}</td>
        </tr>
        <tr>
          <th>City</th>
          <td id='ownerCity'>{owner.city}</td>
        </tr>
        <tr>
          <th>Telephone</th>
          <td id='ownerTelephone'>{owner.telephone}</td>
        </tr>
      </tbody>
    </table>

    <Link to={`/owners/${owner.id}/edit`} className='btn btn-default'>Edit Owner</Link>
    &nbsp;
    <Link to={`/owners/${owner.id}/pets/new`} className='btn btn-default'>Add New Pet</Link>
  </section>
);
