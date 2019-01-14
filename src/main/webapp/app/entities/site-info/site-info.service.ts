import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISiteInfo } from 'app/shared/model/site-info.model';

type EntityResponseType = HttpResponse<ISiteInfo>;
type EntityArrayResponseType = HttpResponse<ISiteInfo[]>;

@Injectable({ providedIn: 'root' })
export class SiteInfoService {
    public resourceUrl = SERVER_API_URL + 'api/site-infos';

    constructor(protected http: HttpClient) {}

    create(siteInfo: ISiteInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(siteInfo);
        return this.http
            .post<ISiteInfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(siteInfo: ISiteInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(siteInfo);
        return this.http
            .put<ISiteInfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISiteInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISiteInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(siteInfo: ISiteInfo): ISiteInfo {
        const copy: ISiteInfo = Object.assign({}, siteInfo, {
            createTime: siteInfo.createTime != null && siteInfo.createTime.isValid() ? siteInfo.createTime.toJSON() : null,
            lastModifyTime: siteInfo.lastModifyTime != null && siteInfo.lastModifyTime.isValid() ? siteInfo.lastModifyTime.toJSON() : null
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
            res.body.forEach((siteInfo: ISiteInfo) => {
                siteInfo.createTime = siteInfo.createTime != null ? moment(siteInfo.createTime) : null;
                siteInfo.lastModifyTime = siteInfo.lastModifyTime != null ? moment(siteInfo.lastModifyTime) : null;
            });
        }
        return res;
    }
}
