import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStoreUser } from 'app/shared/model/store-user.model';

type EntityResponseType = HttpResponse<IStoreUser>;
type EntityArrayResponseType = HttpResponse<IStoreUser[]>;

@Injectable({ providedIn: 'root' })
export class StoreUserService {
    public resourceUrl = SERVER_API_URL + 'api/store-users';

    constructor(protected http: HttpClient) {}

    create(storeUser: IStoreUser): Observable<EntityResponseType> {
        return this.http.post<IStoreUser>(this.resourceUrl, storeUser, { observe: 'response' });
    }

    update(storeUser: IStoreUser): Observable<EntityResponseType> {
        return this.http.put<IStoreUser>(this.resourceUrl, storeUser, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IStoreUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStoreUser[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
