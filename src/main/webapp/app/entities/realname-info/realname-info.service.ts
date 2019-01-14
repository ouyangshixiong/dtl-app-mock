import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRealnameInfo } from 'app/shared/model/realname-info.model';

type EntityResponseType = HttpResponse<IRealnameInfo>;
type EntityArrayResponseType = HttpResponse<IRealnameInfo[]>;

@Injectable({ providedIn: 'root' })
export class RealnameInfoService {
    public resourceUrl = SERVER_API_URL + 'api/realname-infos';

    constructor(protected http: HttpClient) {}

    create(realnameInfo: IRealnameInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(realnameInfo);
        return this.http
            .post<IRealnameInfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(realnameInfo: IRealnameInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(realnameInfo);
        return this.http
            .put<IRealnameInfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRealnameInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRealnameInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(realnameInfo: IRealnameInfo): IRealnameInfo {
        const copy: IRealnameInfo = Object.assign({}, realnameInfo, {
            createTime: realnameInfo.createTime != null && realnameInfo.createTime.isValid() ? realnameInfo.createTime.toJSON() : null,
            lastModifyTime:
                realnameInfo.lastModifyTime != null && realnameInfo.lastModifyTime.isValid() ? realnameInfo.lastModifyTime.toJSON() : null
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
            res.body.forEach((realnameInfo: IRealnameInfo) => {
                realnameInfo.createTime = realnameInfo.createTime != null ? moment(realnameInfo.createTime) : null;
                realnameInfo.lastModifyTime = realnameInfo.lastModifyTime != null ? moment(realnameInfo.lastModifyTime) : null;
            });
        }
        return res;
    }
}
