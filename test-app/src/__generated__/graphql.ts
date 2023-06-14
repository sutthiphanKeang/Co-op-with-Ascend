/* eslint-disable */
import { TypedDocumentNode as DocumentNode } from '@graphql-typed-document-node/core';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string | number; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
};

export type Category = {
  __typename?: 'Category';
  id: Scalars['ID']['output'];
  invoice: Invoice;
  product: Product;
  unit: Scalars['Int']['output'];
};

export type CategoryDto = {
  unit: Scalars['Int']['input'];
};

export type Invoice = {
  __typename?: 'Invoice';
  id: Scalars['ID']['output'];
  status: Scalars['Boolean']['output'];
  user: User;
};

export type InvoiceDto = {
  status: Scalars['Boolean']['input'];
};

export type Mutation = {
  __typename?: 'Mutation';
  createCategory: Category;
  createInvoice: Invoice;
  createProduct: Product;
  createUser: User;
  deleteCategory: Scalars['Boolean']['output'];
  deleteInvoice: Scalars['Boolean']['output'];
  deleteProduct: Scalars['Boolean']['output'];
  deleteUser: Scalars['Boolean']['output'];
  updateInvoice: Invoice;
  updateProduct: Product;
  updateUser: User;
};


export type MutationCreateCategoryArgs = {
  categoryDto: CategoryDto;
  invoiceId: Scalars['ID']['input'];
  productId: Scalars['ID']['input'];
};


export type MutationCreateInvoiceArgs = {
  userId: Scalars['ID']['input'];
};


export type MutationCreateProductArgs = {
  productDto: ProductDto;
};


export type MutationCreateUserArgs = {
  userDto: UserDto;
};


export type MutationDeleteCategoryArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteInvoiceArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteProductArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteUserArgs = {
  id: Scalars['ID']['input'];
};


export type MutationUpdateInvoiceArgs = {
  id: Scalars['ID']['input'];
  invoiceDto: InvoiceDto;
};


export type MutationUpdateProductArgs = {
  id: Scalars['ID']['input'];
  productDto: ProductDto;
};


export type MutationUpdateUserArgs = {
  id: Scalars['ID']['input'];
  userDto: UserDto;
};

export type Product = {
  __typename?: 'Product';
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
  price: Scalars['Int']['output'];
};

export type ProductDto = {
  name: Scalars['String']['input'];
  price: Scalars['Int']['input'];
};

export type Query = {
  __typename?: 'Query';
  getAllInvoices: Array<Invoice>;
  getAllProducts: Array<Product>;
  getAllUsers: Array<User>;
  getCategories: Array<Category>;
  getCategoryById: Category;
  getCategoryByInvoiceId: Array<Category>;
  getCategoryByProductId: Array<Category>;
  getInvoiceById: Invoice;
  getInvoiceByUserId: Array<Invoice>;
  getProductById: Product;
  getUserByEmail?: Maybe<User>;
  getUserById?: Maybe<User>;
};


export type QueryGetCategoryByIdArgs = {
  id: Scalars['ID']['input'];
};


export type QueryGetCategoryByInvoiceIdArgs = {
  id: Scalars['ID']['input'];
};


export type QueryGetCategoryByProductIdArgs = {
  id: Scalars['ID']['input'];
};


export type QueryGetInvoiceByIdArgs = {
  id: Scalars['ID']['input'];
};


export type QueryGetInvoiceByUserIdArgs = {
  id: Scalars['ID']['input'];
};


export type QueryGetProductByIdArgs = {
  id: Scalars['ID']['input'];
};


export type QueryGetUserByEmailArgs = {
  email: Scalars['String']['input'];
};


export type QueryGetUserByIdArgs = {
  id: Scalars['ID']['input'];
};

export type User = {
  __typename?: 'User';
  email: Scalars['String']['output'];
  firstName: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  invoice?: Maybe<Invoice>;
  lastName: Scalars['String']['output'];
  password: Scalars['String']['output'];
  phoneNo: Scalars['String']['output'];
};

export type UserDto = {
  email: Scalars['String']['input'];
  firstName: Scalars['String']['input'];
  lastName: Scalars['String']['input'];
  password: Scalars['String']['input'];
  phoneNo: Scalars['String']['input'];
};

export type GetUserByEmailQueryVariables = Exact<{
  email: Scalars['String']['input'];
}>;


export type GetUserByEmailQuery = { __typename?: 'Query', getUserByEmail?: { __typename?: 'User', id: string, firstName: string, lastName: string, phoneNo: string, email: string, password: string } | null };

export type CreateUserMutationVariables = Exact<{
  userDto: UserDto;
}>;


export type CreateUserMutation = { __typename?: 'Mutation', createUser: { __typename?: 'User', id: string, firstName: string, lastName: string, phoneNo: string, email: string } };

export type UpdateUserMutationVariables = Exact<{
  id: Scalars['ID']['input'];
  userDto: UserDto;
}>;


export type UpdateUserMutation = { __typename?: 'Mutation', updateUser: { __typename?: 'User', id: string, firstName: string, lastName: string, phoneNo: string, email: string } };

export type DeleteUserMutationVariables = Exact<{
  id: Scalars['ID']['input'];
}>;


export type DeleteUserMutation = { __typename?: 'Mutation', deleteUser: boolean };

export type GetInvoiceByUserIdQueryVariables = Exact<{
  id: Scalars['ID']['input'];
}>;


export type GetInvoiceByUserIdQuery = { __typename?: 'Query', getInvoiceByUserId: Array<{ __typename?: 'Invoice', id: string, status: boolean, user: { __typename?: 'User', id: string, firstName: string, lastName: string, phoneNo: string, email: string, password: string } }> };


export const GetUserByEmailDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"query","name":{"kind":"Name","value":"getUserByEmail"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"email"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"String"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"getUserByEmail"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"email"},"value":{"kind":"Variable","name":{"kind":"Name","value":"email"}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"id"}},{"kind":"Field","name":{"kind":"Name","value":"firstName"}},{"kind":"Field","name":{"kind":"Name","value":"lastName"}},{"kind":"Field","name":{"kind":"Name","value":"phoneNo"}},{"kind":"Field","name":{"kind":"Name","value":"email"}},{"kind":"Field","name":{"kind":"Name","value":"password"}}]}}]}}]} as unknown as DocumentNode<GetUserByEmailQuery, GetUserByEmailQueryVariables>;
export const CreateUserDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"mutation","name":{"kind":"Name","value":"CreateUser"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"userDto"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"UserDto"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"createUser"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"userDto"},"value":{"kind":"Variable","name":{"kind":"Name","value":"userDto"}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"id"}},{"kind":"Field","name":{"kind":"Name","value":"firstName"}},{"kind":"Field","name":{"kind":"Name","value":"lastName"}},{"kind":"Field","name":{"kind":"Name","value":"phoneNo"}},{"kind":"Field","name":{"kind":"Name","value":"email"}}]}}]}}]} as unknown as DocumentNode<CreateUserMutation, CreateUserMutationVariables>;
export const UpdateUserDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"mutation","name":{"kind":"Name","value":"updateUser"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"id"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"ID"}}}},{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"userDto"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"UserDto"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"updateUser"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"id"},"value":{"kind":"Variable","name":{"kind":"Name","value":"id"}}},{"kind":"Argument","name":{"kind":"Name","value":"userDto"},"value":{"kind":"Variable","name":{"kind":"Name","value":"userDto"}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"id"}},{"kind":"Field","name":{"kind":"Name","value":"firstName"}},{"kind":"Field","name":{"kind":"Name","value":"lastName"}},{"kind":"Field","name":{"kind":"Name","value":"phoneNo"}},{"kind":"Field","name":{"kind":"Name","value":"email"}}]}}]}}]} as unknown as DocumentNode<UpdateUserMutation, UpdateUserMutationVariables>;
export const DeleteUserDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"mutation","name":{"kind":"Name","value":"DeleteUser"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"id"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"ID"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"deleteUser"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"id"},"value":{"kind":"Variable","name":{"kind":"Name","value":"id"}}}]}]}}]} as unknown as DocumentNode<DeleteUserMutation, DeleteUserMutationVariables>;
export const GetInvoiceByUserIdDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"query","name":{"kind":"Name","value":"getInvoiceByUserId"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"id"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"ID"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"getInvoiceByUserId"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"id"},"value":{"kind":"Variable","name":{"kind":"Name","value":"id"}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"id"}},{"kind":"Field","name":{"kind":"Name","value":"user"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"id"}},{"kind":"Field","name":{"kind":"Name","value":"firstName"}},{"kind":"Field","name":{"kind":"Name","value":"lastName"}},{"kind":"Field","name":{"kind":"Name","value":"phoneNo"}},{"kind":"Field","name":{"kind":"Name","value":"email"}},{"kind":"Field","name":{"kind":"Name","value":"password"}}]}},{"kind":"Field","name":{"kind":"Name","value":"status"}}]}}]}}]} as unknown as DocumentNode<GetInvoiceByUserIdQuery, GetInvoiceByUserIdQueryVariables>;