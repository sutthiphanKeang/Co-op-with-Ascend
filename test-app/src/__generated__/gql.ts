/* eslint-disable */
import * as types from './graphql';
import { TypedDocumentNode as DocumentNode } from '@graphql-typed-document-node/core';

/**
 * Map of all GraphQL operations in the project.
 *
 * This map has several performance disadvantages:
 * 1. It is not tree-shakeable, so it will include all operations in the project.
 * 2. It is not minifiable, so the string of a GraphQL query will be multiple times inside the bundle.
 * 3. It does not support dead code elimination, so it will add unused operations.
 *
 * Therefore it is highly recommended to use the babel or swc plugin for production.
 */
const documents = {
    "\n  query getUserByEmail($email: String!) {\n    getUserByEmail(email: $email) {\n      id\n      firstName\n      lastName\n      phoneNo\n      email\n      password\n    }\n  }\n": types.GetUserByEmailDocument,
    "\n  mutation CreateUser($userDto: UserDto!) {\n    createUser(userDto: $userDto) {\n      id\n      firstName\n      lastName\n      phoneNo\n      email\n    }\n  }\n": types.CreateUserDocument,
    "\n    query getUserByEmail($email: String!) {\n      getUserByEmail(email: $email) {\n        id\n        firstName\n        lastName\n        phoneNo\n        email\n        password\n      }\n    }\n  ": types.GetUserByEmailDocument,
    "\n    mutation updateUser($id: ID!, $userDto: UserDto!) {\n      updateUser(id: $id, userDto: $userDto) {\n        id\n        firstName\n        lastName\n        phoneNo\n        email\n      }\n    }\n  ": types.UpdateUserDocument,
    "\n    mutation DeleteUser($id: ID!) {\n      deleteUser(id: $id)\n    }\n  ": types.DeleteUserDocument,
    "\n    query getInvoiceByUserId($id: ID!) {\n      getInvoiceByUserId(id: $id) {\n        id\n        user {\n          id\n          firstName\n          lastName\n          phoneNo\n          email\n          password\n        }\n        status\n      }\n    }\n  ": types.GetInvoiceByUserIdDocument,
};

/**
 * The gql function is used to parse GraphQL queries into a document that can be used by GraphQL clients.
 *
 *
 * @example
 * ```ts
 * const query = gql(`query GetUser($id: ID!) { user(id: $id) { name } }`);
 * ```
 *
 * The query argument is unknown!
 * Please regenerate the types.
 */
export function gql(source: string): unknown;

/**
 * The gql function is used to parse GraphQL queries into a document that can be used by GraphQL clients.
 */
export function gql(source: "\n  query getUserByEmail($email: String!) {\n    getUserByEmail(email: $email) {\n      id\n      firstName\n      lastName\n      phoneNo\n      email\n      password\n    }\n  }\n"): (typeof documents)["\n  query getUserByEmail($email: String!) {\n    getUserByEmail(email: $email) {\n      id\n      firstName\n      lastName\n      phoneNo\n      email\n      password\n    }\n  }\n"];
/**
 * The gql function is used to parse GraphQL queries into a document that can be used by GraphQL clients.
 */
export function gql(source: "\n  mutation CreateUser($userDto: UserDto!) {\n    createUser(userDto: $userDto) {\n      id\n      firstName\n      lastName\n      phoneNo\n      email\n    }\n  }\n"): (typeof documents)["\n  mutation CreateUser($userDto: UserDto!) {\n    createUser(userDto: $userDto) {\n      id\n      firstName\n      lastName\n      phoneNo\n      email\n    }\n  }\n"];
/**
 * The gql function is used to parse GraphQL queries into a document that can be used by GraphQL clients.
 */
export function gql(source: "\n    query getUserByEmail($email: String!) {\n      getUserByEmail(email: $email) {\n        id\n        firstName\n        lastName\n        phoneNo\n        email\n        password\n      }\n    }\n  "): (typeof documents)["\n    query getUserByEmail($email: String!) {\n      getUserByEmail(email: $email) {\n        id\n        firstName\n        lastName\n        phoneNo\n        email\n        password\n      }\n    }\n  "];
/**
 * The gql function is used to parse GraphQL queries into a document that can be used by GraphQL clients.
 */
export function gql(source: "\n    mutation updateUser($id: ID!, $userDto: UserDto!) {\n      updateUser(id: $id, userDto: $userDto) {\n        id\n        firstName\n        lastName\n        phoneNo\n        email\n      }\n    }\n  "): (typeof documents)["\n    mutation updateUser($id: ID!, $userDto: UserDto!) {\n      updateUser(id: $id, userDto: $userDto) {\n        id\n        firstName\n        lastName\n        phoneNo\n        email\n      }\n    }\n  "];
/**
 * The gql function is used to parse GraphQL queries into a document that can be used by GraphQL clients.
 */
export function gql(source: "\n    mutation DeleteUser($id: ID!) {\n      deleteUser(id: $id)\n    }\n  "): (typeof documents)["\n    mutation DeleteUser($id: ID!) {\n      deleteUser(id: $id)\n    }\n  "];
/**
 * The gql function is used to parse GraphQL queries into a document that can be used by GraphQL clients.
 */
export function gql(source: "\n    query getInvoiceByUserId($id: ID!) {\n      getInvoiceByUserId(id: $id) {\n        id\n        user {\n          id\n          firstName\n          lastName\n          phoneNo\n          email\n          password\n        }\n        status\n      }\n    }\n  "): (typeof documents)["\n    query getInvoiceByUserId($id: ID!) {\n      getInvoiceByUserId(id: $id) {\n        id\n        user {\n          id\n          firstName\n          lastName\n          phoneNo\n          email\n          password\n        }\n        status\n      }\n    }\n  "];

export function gql(source: string) {
  return (documents as any)[source] ?? {};
}

export type DocumentType<TDocumentNode extends DocumentNode<any, any>> = TDocumentNode extends DocumentNode<  infer TType,  any>  ? TType  : never;