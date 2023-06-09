/* eslint-disable */
import * as types from './graphql';
import { TypedDocumentNode as DocumentNode } from '@graphql-typed-document-node/core';

const documents: Record<string, DocumentNode<any, any>> = {};

/**
 * The gql function is used to parse GraphQL queries into a document that can be used by GraphQL clients.
 *
 * @param source The GraphQL query string
 * @returns The parsed DocumentNode or undefined if not found
 *
 * @example
 * ```ts
 * const query = gql(`query GetUser($id: ID!) { user(id: $id) { name } }`);
 * ```
 */
export function gql<TType = unknown>(source: string): DocumentNode<TType, unknown> | undefined {
  return documents[source];
}

export type DocumentType<TDocumentNode extends DocumentNode<any, any>> = TDocumentNode extends DocumentNode<
  infer TType,
  any
>
  ? TType
  : never;