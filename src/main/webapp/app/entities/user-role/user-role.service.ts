import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUserRole } from 'app/shared/model/user-role.model';

type EntityResponseType = HttpResponse<IUserRole>;
type EntityArrayResponseType = HttpResponse<IUserRole[]>;

@Injectable({ providedIn: 'root' })
export class UserRoleService {
    public resourceUrl = SERVER_API_URL + 'api/user-roles';

    constructor(protected http: HttpClient) {}

    create(userRole: IUserRole): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(userRole);
        return this.http
            .post<IUserRole>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(userRole: IUserRole): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(userRole);
        return this.http
            .put<IUserRole>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IUserRole>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUserRole[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(userRole: IUserRole): IUserRole {
        const copy: IUserRole = Object.assign({}, userRole, {
            createTime: userRole.createTime != null && userRole.createTime.isValid() ? userRole.createTime.toJSON() : null,
            lastModifyTime: userRole.lastModifyTime != null && userRole.lastModifyTime.isValid() ? userRole.lastModifyTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.lastModifyTime = res.body.lastModifyTime != null ? moment(res.body.lastModifyTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((userRole: IUserRole) => {
                userRole.createTime = userRole.createTime != null ? moment(userRole.createTime) : null;
                userRole.lastModifyTime = userRole.lastModifyTime != null ? moment(userRole.lastModifyTime) : null;
            });
        }
        return res;
    }
}
