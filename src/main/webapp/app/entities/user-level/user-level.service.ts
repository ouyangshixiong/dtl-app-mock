import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUserLevel } from 'app/shared/model/user-level.model';

type EntityResponseType = HttpResponse<IUserLevel>;
type EntityArrayResponseType = HttpResponse<IUserLevel[]>;

@Injectable({ providedIn: 'root' })
export class UserLevelService {
    public resourceUrl = SERVER_API_URL + 'api/user-levels';

    constructor(protected http: HttpClient) {}

    create(userLevel: IUserLevel): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(userLevel);
        return this.http
            .post<IUserLevel>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(userLevel: IUserLevel): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(userLevel);
        return this.http
            .put<IUserLevel>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IUserLevel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUserLevel[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(userLevel: IUserLevel): IUserLevel {
        const copy: IUserLevel = Object.assign({}, userLevel, {
            createTime: userLevel.createTime != null && userLevel.createTime.isValid() ? userLevel.createTime.toJSON() : null,
            lastModifyTime:
                userLevel.lastModifyTime != null && userLevel.lastModifyTime.isValid() ? userLevel.lastModifyTime.toJSON() : null
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
            res.body.forEach((userLevel: IUserLevel) => {
                userLevel.createTime = userLevel.createTime != null ? moment(userLevel.createTime) : null;
                userLevel.lastModifyTime = userLevel.lastModifyTime != null ? moment(userLevel.lastModifyTime) : null;
            });
        }
        return res;
    }
}
