import { gql } from '../__generated__/gql';

export const userLogin = gql(/* GraphQL */`
query  getAllUsers{
      id
      firstName
      lastName
      phoneNo
      email
      password
    }
`);