/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { SiteAuthRecordDetailComponent } from 'app/entities/site-auth-record/site-auth-record-detail.component';
import { SiteAuthRecord } from 'app/shared/model/site-auth-record.model';

describe('Component Tests', () => {
    describe('SiteAuthRecord Management Detail Component', () => {
        let comp: SiteAuthRecordDetailComponent;
        let fixture: ComponentFixture<SiteAuthRecordDetailComponent>;
        const route = ({ data: of({ siteAuthRecord: new SiteAuthRecord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [SiteAuthRecordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SiteAuthRecordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SiteAuthRecordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.siteAuthRecord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
