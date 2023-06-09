/* eslint-disable */
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
  getInvoiceById: Invoice;
  getProductById: Product;
  getUserByEmail?: Maybe<User>;
  getUserById?: Maybe<User>;
};


export type QueryGetCategoryByIdArgs = {
  id: Scalars['ID']['input'];
};


export type QueryGetInvoiceByIdArgs = {
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
