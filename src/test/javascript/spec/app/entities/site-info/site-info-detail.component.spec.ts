/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { SiteInfoDetailComponent } from 'app/entities/site-info/site-info-detail.component';
import { SiteInfo } from 'app/shared/model/site-info.model';

describe('Component Tests', () => {
    describe('SiteInfo Management Detail Component', () => {
        let comp: SiteInfoDetailComponent;
        let fixture: ComponentFixture<SiteInfoDetailComponent>;
        const route = ({ data: of({ siteInfo: new SiteInfo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [SiteInfoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SiteInfoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SiteInfoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.siteInfo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
