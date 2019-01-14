/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { SiteInfoComponent } from 'app/entities/site-info/site-info.component';
import { SiteInfoService } from 'app/entities/site-info/site-info.service';
import { SiteInfo } from 'app/shared/model/site-info.model';

describe('Component Tests', () => {
    describe('SiteInfo Management Component', () => {
        let comp: SiteInfoComponent;
        let fixture: ComponentFixture<SiteInfoComponent>;
        let service: SiteInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [SiteInfoComponent],
                providers: []
            })
                .overrideTemplate(SiteInfoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SiteInfoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SiteInfoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SiteInfo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.siteInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
