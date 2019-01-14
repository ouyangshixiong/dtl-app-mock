import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBankcardInfo } from 'app/shared/model/bankcard-info.model';

type EntityResponseType = HttpResponse<IBankcardInfo>;
type EntityArrayResponseType = HttpResponse<IBankcardInfo[]>;

@Injectable({ providedIn: 'root' })
export class BankcardInfoService {
    public resourceUrl = SERVER_API_URL + 'api/bankcard-infos';

    constructor(protected http: HttpClient) {}

    create(bankcardInfo: IBankcardInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(bankcardInfo);
        return this.http
            .post<IBankcardInfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(bankcardInfo: IBankcardInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(bankcardInfo);
        return this.http
            .put<IBankcardInfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBankcardInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBankcardInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(bankcardInfo: IBankcardInfo): IBankcardInfo {
        const copy: IBankcardInfo = Object.assign({}, bankcardInfo, {
            createTime: bankcardInfo.createTime != null && bankcardInfo.createTime.isValid() ? bankcardInfo.createTime.toJSON() : null,
            lastModifyTime:
                bankcardInfo.lastModifyTime != null && bankcardInfo.lastModifyTime.isValid() ? bankcardInfo.lastModifyTime.toJSON() : null
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
            res.body.forEach((bankcardInfo: IBankcardInfo) => {
                bankcardInfo.createTime = bankcardInfo.createTime != null ? moment(bankcardInfo.createTime) : null;
                bankcardInfo.lastModifyTime = bankcardInfo.lastModifyTime != null ? moment(bankcardInfo.lastModifyTime) : null;
            });
        }
        return res;
    }
}
