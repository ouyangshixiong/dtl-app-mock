export interface IStoreUser {
    id?: number;
    storeId?: number;
    userId?: number;
}

export class StoreUser implements IStoreUser {
    constructor(public id?: number, public storeId?: number, public userId?: number) {}
}
