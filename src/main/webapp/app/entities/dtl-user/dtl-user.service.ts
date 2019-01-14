import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDTLUser } from 'app/shared/model/dtl-user.model';

type EntityResponseType = HttpResponse<IDTLUser>;
type EntityArrayResponseType = HttpResponse<IDTLUser[]>;

@Injectable({ providedIn: 'root' })
export class DTLUserService {
    public resourceUrl = SERVER_API_URL + 'api/dtl-users';

    constructor(protected http: HttpClient) {}

    create(dTLUser: IDTLUser): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dTLUser);
        return this.http
            .post<IDTLUser>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(dTLUser: IDTLUser): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(dTLUser);
        return this.http
            .put<IDTLUser>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDTLUser>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDTLUser[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(dTLUser: IDTLUser): IDTLUser {
        const copy: IDTLUser = Object.assign({}, dTLUser, {
            regTime: dTLUser.regTime != null && dTLUser.regTime.isValid() ? dTLUser.regTime.toJSON() : null,
            lastLoginTime: dTLUser.lastLoginTime != null && dTLUser.lastLoginTime.isValid() ? dTLUser.lastLoginTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.regTime = res.body.regTime != null ? moment(res.body.regTime) : null;
            res.body.lastLoginTime = res.body.lastLoginTime != null ? moment(res.body.lastLoginTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((dTLUser: IDTLUser) => {
                dTLUser.regTime = dTLUser.regTime != null ? moment(dTLUser.regTime) : null;
                dTLUser.lastLoginTime = dTLUser.lastLoginTime != null ? moment(dTLUser.lastLoginTime) : null;
            });
        }
        return res;
    }
}
