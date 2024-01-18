export type Taux={
    id:number;
    label:string;
    description:string;
    year:string;
    amount:number;
    payementType:'PAYED'|'NOT_PAYED'|'LATE';
    dueTime:string;
    createdAt:string;
}
