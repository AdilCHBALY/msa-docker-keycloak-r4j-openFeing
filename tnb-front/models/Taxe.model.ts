import { Category } from "./Category.model";

export type Taxe={
    id:number;
    taux:number;
    createdAt:string;
    category:Category
}